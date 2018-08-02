package com.snowwolf.dtotool.tool;

import com.snowwolf.dtotool.mode.ParamVo;
import com.snowwolf.dtotool.view.ColumInfoView;
import com.snowwolf.dtotool.view.ColumView;
import com.snowwolf.dtotool.view.JsonView;
import com.snowwolf.dtotool.yml.GetNameYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/22 14:58
 * @modified by:
 * @versions：0.1.0
 */
@Slf4j
public class BeanUtil {
    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String COLUMN_NAME = "COLUMN_NAME";
    private static final String DATA_TYPE = "DATA_TYPE";
    private static final String DATA_SCALE = "DATA_SCALE";
    private static final String ISPK = "ISPK";
    private static final String COMMENTS = "COMMENTS";
    private static final String ABLENULL = "NO";
    public static String createBean(ViewInfo viewInfo){
        ColumView columView = viewInfo.getColumView();
        StringBuffer result = new StringBuffer();
        StringBuffer entityHeader = new StringBuffer();
        StringBuffer getsetresult = new StringBuffer();
        String fileName = "";

        List<String> fale = new ArrayList<>();
        Map<String,String> propertyMap = new HashMap<>();
        try{

            List<Map> ls = new ArrayList<Map>();
            String tableName = viewInfo.getColumView().getTableName();
            //设置创建实体类的类名，当没有传参数，根据yml配置规则来命名，$代表表名
            String className = viewInfo.getClassName();
            if(StringUtils.isEmpty(className)){
                String arr[] = tableName.split("_");
                String tbName = "";
                for(int j = 0;j <arr.length;j++){
                    tbName += arr[j].replaceFirst(arr[j].substring(0, 1),arr[j].substring(0, 1).toUpperCase());
                }
                String name = GetNameYml.getEntity();
                className = GetNameYml.getEntity().replace("$",tbName);
            }
            String entityName = className;
            //设置实体类的包路径，当为空根据yml配置url命名来设置，当保存的路径包含"main\\java\\",设置包路径就是java后面的路径，没有包含，就是path。
            String packageName = viewInfo.getPackageName();
            String path = viewInfo.getPath();
            if(StringUtils.isEmpty(packageName)){
                String url = GetNameYml.getUrl();
                int index = path.indexOf(GetNameYml.getUrl());
                if(index<0){
                    packageName = path.substring(path.indexOf(url)+10,path.length()).replace("\\",".");
                }else{
                    packageName = path.replace("\\",".");
                    packageName = packageName.substring(packageName.indexOf(":.")+2,packageName.length());
                }
            }
            columView.getList().forEach(columInfoView -> {
                Map map = new HashMap<String,Object>();
                map.put(BeanUtil.TABLE_NAME, tableName);
                map.put(BeanUtil.COLUMN_NAME, columInfoView.getColumnName());
                map.put(BeanUtil.DATA_TYPE, columInfoView.getDataType());
                map.put(BeanUtil.DATA_SCALE, columInfoView.getMaxCharver());
                map.put(BeanUtil.ABLENULL, columInfoView.getAbleNull());
                if(!StringUtils.isEmpty(columInfoView.getColumnKey())){
                    map.put(BeanUtil.ISPK, columInfoView.getColumnKey());
                }
                if(!StringUtils.isEmpty(columInfoView.getColumnComment())){
                    map.put(BeanUtil.COMMENTS, columInfoView.getColumnComment());
                }
                ls.add(map);
            });
            result.append("package ").append(packageName).append(";\n");
            //实体类引入的注解对应的包

            if(!CollectionUtils.isEmpty(viewInfo.getTagVo().getEntityTag())){
                viewInfo.getTagVo().getEntityTag().forEach(tagInfo -> {
                    result.append(tagInfo.getImportUrl()+";\n");
                    if(tagInfo.getName().equals("Entity")){
                        entityHeader.append("@Entity\n");
                    }
                    if(tagInfo.getName().equals("Table")){
                        entityHeader.append("@Table(name=\"" + tableName + "\")\n");
                    }
                    //@ApiModel(value="ActivityEo",description = "活动基本信息")
                    if(tagInfo.getName().equals("ApiModel")){
                        entityHeader.append("@ApiModel(value=\"" + entityName + "\" , " +
                                " description = \"" + viewInfo.getColumView().getTableDesc() + "\" )\n");
                    }
                    if(tagInfo.getName().equals("Data")){
                        fale.add(tagInfo.getName());
                    }
                });
            }
            if(!CollectionUtils.isEmpty(viewInfo.getTagVo().getPropertyTag())){
                viewInfo.getTagVo().getPropertyTag().forEach(tagInfo -> {
                    result.append(tagInfo.getImportUrl()+";\n");
                    propertyMap.put(tagInfo.getName(),tagInfo.getImportUrl());
                });
            }
//            result.append("import java.io.Serializable;\n");
//            result.append("import javax.persistence.Column;\n");
//            result.append("import javax.persistence.Entity;\n");
//            result.append("import javax.persistence.Id;\n");
//            result.append("import javax.persistence.Table;\n\n");
            //实体类引入的注解
//            result.append("@Entity\n")
//                    .append("@Table(name=\"" + tableName + "\")\n")
            result.append(entityHeader).append("public class " + className + " implements Serializable{\n");

            for(int i=0; i<ls.size(); i++){
                Map map = ls.get(i);
                String columnName = map.get(BeanUtil.COLUMN_NAME).toString();
                String arr[] = columnName.split("_");
                String varName = arr[0];
                for(int j = 1;j <arr.length;j++){
                    varName += arr[j].replaceFirst(arr[j].substring(0, 1),arr[j].substring(0, 1).toUpperCase());
                }
                String getsetName=varName.replaceFirst(varName.substring(0, 1),varName.substring(0, 1).toUpperCase());
                String comments = "";
                if(map.get(BeanUtil.COMMENTS) != null){
                    comments = map.get(BeanUtil.COMMENTS).toString();
                }
                String dataType = map.get(BeanUtil.DATA_TYPE).toString();
                Boolean isPK = false;
                if("PRI".equals(map.get(BeanUtil.ISPK))){
                    isPK = true;
                }
                String varType = null;
                dataType = dataType.toUpperCase();
                if(dataType.equals("DATE")){
                    varType = "Date";
                }else if(dataType.equals("DOUBLE")){
                    varType = "Double";
                }else if(dataType.equals("TINYINT")||dataType.equals("SMALLINT")||
                        dataType.equals("MEDIUMINT")|| dataType.equals("INT")|| dataType.equals("INTEGER")){
                    varType = "Integer";
                }else if(dataType.equals("BIGINT")){
                    varType = "Long";
                }else if(dataType.equals("FLOAT")){
                    varType = "Float";
                }else{
                    varType = "String";
                }
                if(!StringUtils.isEmpty(comments)){
                    result.append("\t /**"+"\n");
                    result.append("\t  * "+ comments+"\n");
                    result.append("\t  */ \n");
                }
                if(isPK){
                    result.append("\t @Id\n");
                }
                if(propertyMap.get("NotNull") != null){
                    if(map.get("ABLENULL") != null && "NO".equals(map.get("ABLENULL"))){
                        result.append("\t @NotNull\n");
                    }
                }
                if(propertyMap.get("ApiModelProperty") != null){
                    result.append("\t @ApiModelProperty(name=\"" + varName + "\",value =\""+map.get(COMMENTS)+"\")" + "\n");
                }
                if(propertyMap.get("Column") != null){
                    //字段引入的注解
                    result.append("\t @Column(name=\"" + columnName + "\")" + "\n");
                }
                result.append("\t private  " + varType + "  " + varName + ";\n\n");
                //当引入Data注解，get/ser方法不需要了。
                if(CollectionUtils.isEmpty(fale)){
                    getsetresult.append("\t public " + varType + " " + "get"+getsetName +"(){\n")
                            .append("\t\t return "+varName+";"+"\n")
                            .append("\t } \n\n")
                            .append("\t public void " + "set"+getsetName +"("+varType + " " + varName +"){"+"\n")
                            .append("\t\t"+"this."+varName+"="+varName+";"+"\n")
                            .append("\t } \n\n");
                }
            }
            result.append(getsetresult).append("}");

            File file = new File(path + className + ".java");
            fileName = file.getName();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(result.toString());
            bw.flush();
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 创建mock接口请求参数对应的bean
     * @param paramsInfo
     * @return
     */
    public static String createBeanForMockReqParams(ParamsInfo paramsInfo){
        String path = paramsInfo.getPath();
        String className = paramsInfo.getClassName();
        String packageName = paramsInfo.getPackageName();
        String fileName = "";
        StringBuffer entityHeader = new StringBuffer();
        StringBuffer getSetBf = new StringBuffer();
        StringBuffer result = new StringBuffer();
        try{
            //设置创建实体类的类名，当没有传参数，根据yml配置规则来命名，$代表表名
            String entityName = className;
            //设置实体类的包路径，当为空根据yml配置url命名来设置，当保存的路径包含"main\\java\\",设置包路径就是java后面的路径，没有包含，就是path。
            if(StringUtils.isEmpty(packageName)){
                String url = GetNameYml.getUrl();
                int index = path.indexOf(GetNameYml.getUrl());
                if(index<0){
                    packageName = path.substring(path.indexOf(url)+10,path.length()).replace("\\",".");
                }else{
                    packageName = path.replace("\\",".");
                    packageName = packageName.substring(packageName.indexOf(":.")+2,packageName.length());
                }
            }
            entityHeader.append("public class " + className + " implements Serializable{\n");
            paramsInfo.getList().forEach(paramVo -> {
                String name = paramVo.getName();
                String type = paramVo.getType();
                String desc = paramVo.getDesc();
                boolean notNull = paramVo.isNotNull();
                String getsetName=name.replaceFirst(name.substring(0, 1),name.substring(0, 1).toUpperCase());
                String varType = null;
                type = type.toUpperCase();
                if(type.equals("DATE")){
                    varType = "Date";
                }else if(type.equals("DOUBLE")){
                    varType = "Double";
                }else if(type.equals("TINYINT")|| type.equals("INT")){
                    varType = "Integer";
                }else if(type.equals("BIGINT")){
                    varType = "Long";
                }else if(type.equals("FLOAT")){
                    varType = "Float";
                }else{
                    varType = "String";
                }
                if(!StringUtils.isEmpty(desc)){
                    result.append("\t /**"+"\n");
                    result.append("\t  * "+ desc+"\n");
                    result.append("\t  */ \n");
                }
                if(notNull){
                    result.append("\t @NotNull\n");
                }
                result.append("\t private  " + varType + "  " + name + ";\n\n");

                getSetBf.append("\t public " + varType + " " + "get"+getsetName +"(){\n")
                        .append("\t\t return "+name+";"+"\n")
                        .append("\t } \n\n")
                        .append("\t public void " + "set"+getsetName +"("+varType + " " + name +"){"+"\n")
                        .append("\t\t"+"this."+name+"="+name+";"+"\n")
                        .append("\t } \n\n");
            });

            entityHeader.append(result).append(getSetBf).append("}");
            File file = new File(path+"\\" + className + ".java");
            fileName = file.getName();
            file.getPath();
            file.getCanonicalPath();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(entityHeader.toString());
            bw.flush();
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileName;
    }

    public static String createBeanForMockeRes(){
        return null;
    }

    public static List<JsonView> tableToJson(List<ColumInfoView> columInfoViews, String type){
        List<JsonView> list = new ArrayList<>();
        columInfoViews.forEach(columInfoView -> {
            JsonView jsonView = new JsonView();
            if(type.equals("1")){
                jsonView.setDesc(columInfoView.getColumnComment());
            }
            String arr[] = columInfoView.getColumnName().split("_");
            String columnName = arr[0];
            for(int j = 1;j <arr.length;j++){
                columnName += arr[j].replaceFirst(arr[j].substring(0, 1),arr[j].substring(0, 1).toUpperCase());
            }
            jsonView.setName(columnName);
            String dataType = columInfoView.getDataType().toUpperCase();
            String value = "";
            if(dataType.equals("DATETIME")){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value = formatter.format(new Date());
            }else if(dataType.equals("DOUBLE")||dataType.equals("FLOAT")){
                value = "3.01";
            }else if(dataType.equals("TINYINT")||dataType.equals("SMALLINT")||
                    dataType.equals("MEDIUMINT")|| dataType.equals("INT")|| dataType.equals("INTEGER")){
                value = "201";
            }else if(dataType.equals("BIGINT")){
                value = "361222";
            }else{
                value = "测试值";
            }
            jsonView.setValue(value);
            list.add(jsonView);
        });
        return list;
    }

}

