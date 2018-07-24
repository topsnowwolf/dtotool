package com.snowwolf.dtotool.tool;

import com.snowwolf.dtotool.view.ColumView;
import com.snowwolf.dtotool.yml.GetNameYml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/22 14:58
 * @modified by:
 * @versions：0.1.0
 */
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
                    packageName = path.replace("\\",".");
                }else {
                    packageName = path.substring(path.indexOf("main\\java\\")+10,path.length()-1).replace("\\",".");
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
                String comments = map.get(BeanUtil.COMMENTS).toString();
                if(comments==null || comments.equals("")) {
                    comments = "无";
                }
                String dataType = map.get(BeanUtil.DATA_TYPE).toString();
                Boolean isPK = false;
                if(!StringUtils.isEmpty(map.get(BeanUtil.ISPK))){
                    isPK = true;
                }
                String varType = null;
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
}

