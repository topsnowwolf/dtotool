package com.snowwolf.dtotool.business.impl;

import com.snowwolf.dtotool.business.ISchemataApi;
import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.dto.TagReq;
import com.snowwolf.dtotool.service.IAllocationService;
import com.snowwolf.dtotool.service.IColumnService;
import com.snowwolf.dtotool.service.ISchemataService;
import com.snowwolf.dtotool.service.ITableService;
import com.snowwolf.dtotool.tool.TagInfo;
import com.snowwolf.dtotool.tool.TagVo;
import com.snowwolf.dtotool.tool.ViewInfo;
import com.snowwolf.dtotool.view.ColumView;
import com.snowwolf.dtotool.view.SchemataView;
import com.snowwolf.dtotool.view.TableView;
import com.snowwolf.dtotool.view.tag.AnnotationView;
import com.snowwolf.dtotool.yml.rule.GetAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/8/4 9:19
 * @modified by:
 * @versions：0.1.0
 */
@Slf4j
@Service
public class SchemataApiImpl  implements ISchemataApi {
    @Resource
    private ITableService tableService;
    @Resource
    private IAllocationService allocationService;
    @Resource
    private IColumnService columnService;
    @Resource
    private ISchemataService schemataService;
    @Resource
    private GetAnnotation getAnnotationYml;
    @Override
    public SchemataView getAll() {
        return schemataService.getAll();
    }

    @Override
    public TableView findTableByDB(String dbName) {
        return tableService.findTableByDB(dbName);
    }

    @Override
    public ColumView findColumByTB(TableReq tableReq) {
        return columnService.findTableByTB(tableReq);
    }

    @Override
    public String createBean(TagReq tagReq) {
        log.info("come in createBean");
        ViewInfo viewInfo = new ViewInfo();
        ColumView columView = new ColumView();
        columView.setDbName(tagReq.getDbName());
        columView.setTableName(tagReq.getTableName());
        viewInfo.setColumView(columView);
        TagVo tagVo = new TagVo();
        List<TagInfo> entityTag = new ArrayList<>();
        tagReq.getEntityList().forEach(s -> {
            TagInfo tagInfo = new TagInfo();
            tagInfo.setName(s);
            entityTag.add(tagInfo);
        });
        tagVo.setEntityTag(entityTag);
        List<TagInfo> propertyTag = new ArrayList<>();
        tagReq.getPropertyList().forEach(s -> {
            TagInfo tagInfo = new TagInfo();
            tagInfo.setName(s);
            propertyTag.add(tagInfo);
        });
        tagVo.setPropertyTag(propertyTag);
        viewInfo.setTagVo(tagVo);
        viewInfo.setClassName(tagReq.getClassName());
        viewInfo.setPackageName(tagReq.getPackageName());
        viewInfo.setPath(tagReq.getPath());
        return allocationService.createBean(viewInfo);
    }

    @Override
    public AnnotationView findAnnotation() {
        Map<String,String> defaultEntityMap = getAnnotationYml.getDefaultEntityMap();
        Map<String,String> defaultPropertyMap = getAnnotationYml.getDefaultPropertyMap();
        Map<String,String> customEntityMap = getAnnotationYml.getCustomEntityMap();
        Map<String,String> custPropertyMap = getAnnotationYml.getCustomPropertyMap();
        List<String> EntityList = new ArrayList<>();
        List<String> ColumList = new ArrayList<>();
        defaultEntityMap.forEach((s, s2) -> {
            EntityList.add(s);
        });
        if(customEntityMap!=null){
            customEntityMap.forEach((s, s2) -> {
                EntityList.add(s);
            });
        }
        defaultPropertyMap.forEach((s, s2) -> {
            ColumList.add(s);
        });
        if(custPropertyMap!=null){
            custPropertyMap.forEach((s, s2) -> {
                ColumList.add(s);
            });
        }
        AnnotationView view = new AnnotationView();
        view.setColumList(ColumList);
        view.setEntityList(EntityList);
        return view;
    }
}
