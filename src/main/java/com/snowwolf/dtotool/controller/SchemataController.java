package com.snowwolf.dtotool.controller;

import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.dto.TagReq;
import com.snowwolf.dtotool.service.IAllocationService;
import com.snowwolf.dtotool.service.IColumnService;
import com.snowwolf.dtotool.service.ISchemataService;
import com.snowwolf.dtotool.service.ITableService;
import com.snowwolf.dtotool.tool.*;
import com.snowwolf.dtotool.view.*;
import com.snowwolf.dtotool.yml.GetAnnotationYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: topsnowwolf
 * @description: 查询
 * @date: Create in 2018/7/21 18:43
 * @modified by:
 * @versions：0.1.0
 */
@Slf4j
@RestController
@RequestMapping("/db")
public class SchemataController {
    @Autowired
    private ISchemataService schemataService;
    @Autowired
    private ITableService tableService;
    @Autowired
    private IColumnService columnService;
    @Autowired
    private GetAnnotationYml getAnnotationYml;
    @Autowired
    private IAllocationService allocationService;
    /**
     * 查询所有的库
     * @return
     */
    @GetMapping("/findAllDB")
    public SchemataView findAll(){
        log.info("come in SchemataView");
        return schemataService.getAll();
    }

    /**
     * 查询数据库下的表
     * @param dbName
     * @return
     */
    @GetMapping("/findTable")
    public TableView findTableByDB(String dbName){
        log.info("come in findTableByDB");
        return tableService.findTableByDB(dbName);
    }

    /**
     * 查询表的结构
     * @param tableReq
     * @return
     */
    @GetMapping("/findColum")
    public ColumView findColumByTB(TableReq tableReq){
        log.info("come in findColumByTB");
        return columnService.findTableByTB(tableReq);
    }

    /**
     * create
     * @param tagReq
     */
    @PostMapping("/create")
    public DataVo createBean(@RequestBody TagReq tagReq){
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
        String url = allocationService.createBean(viewInfo);
        return new DataVo("0000","SUCCESS",url);
    }
}
