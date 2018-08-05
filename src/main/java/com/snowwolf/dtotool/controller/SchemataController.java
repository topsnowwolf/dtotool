package com.snowwolf.dtotool.controller;

import com.snowwolf.dtotool.business.ISchemataApi;
import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.dto.TagReq;
import com.snowwolf.dtotool.view.ColumView;
import com.snowwolf.dtotool.view.DataVo;
import com.snowwolf.dtotool.view.SchemataView;
import com.snowwolf.dtotool.view.TableView;
import com.snowwolf.dtotool.view.tag.AnnotationView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private ISchemataApi schemataApi;

    /**
     * 查询所有的库
     * @return
     */
    @GetMapping("/findAllDB")
    public SchemataView findAll(){
        log.info("come in SchemataView");
        return schemataApi.getAll();
    }

    /**
     * 查询数据库下的表
     * @param dbName
     * @return
     */
    @GetMapping("/findTable")
    public TableView findTableByDB(String dbName){
        log.info("come in findTableByDB");
        return schemataApi.findTableByDB(dbName);
    }

    /**
     * 查询表的结构
     * @param tableReq
     * @return
     */
    @GetMapping("/findColum")
    public ColumView findColumByTB(TableReq tableReq){
        log.info("come in findColumByTB");
        return schemataApi.findColumByTB(tableReq);
    }

    /**
     * create
     * @param tagReq
     */
    @PostMapping("/create")
    public DataVo createBean(@RequestBody TagReq tagReq){
        String url = schemataApi.createBean(tagReq);
        return new DataVo("0000","SUCCESS",url);
    }

    /**
     * 查询配置的标签
     * @return
     */
    @GetMapping("/tag")
    public DataVo findAnnotation(){
        AnnotationView view = schemataApi.findAnnotation();
        return new DataVo("0000","SUCCESS",view);
    }
}
