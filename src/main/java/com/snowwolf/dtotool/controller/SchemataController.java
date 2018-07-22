package com.snowwolf.dtotool.controller;

import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.mapper.schemata.SchemataMapper;
import com.snowwolf.dtotool.mode.SchemataVo;
import com.snowwolf.dtotool.service.IColumnService;
import com.snowwolf.dtotool.service.ISchemataService;
import com.snowwolf.dtotool.service.ITableService;
import com.snowwolf.dtotool.tool.BeanUtil;
import com.snowwolf.dtotool.view.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查询所有的库
     * @return
     */
    @GetMapping("/findAllDB")
    public SchemataView findAll(){
        return schemataService.getAll();
    }

    /**
     * 查询数据库下的表
     * @param dbName
     * @return
     */
    @GetMapping("/findTable")
    public TableView findTableByDB(String dbName){
        log.info("----");
        return tableService.findTableByDB(dbName);
    }

    /**
     * 查询表的结构
     * @param tableReq
     * @return
     */
    @GetMapping("/findColum")
    public ColumView findColumByTB(TableReq tableReq){
        return columnService.findTableByTB(tableReq);
    }

    /**
     * create
     * @param tableReq
     */
    @GetMapping("/create")
    public void createBean(TableReq tableReq){
        //bean存放路径
        BeanUtil.createBean(columnService.findTableByTB(tableReq),"","","I:\\mycode\\mongodbit\\src\\main\\java\\com\\wolf\\mongodbit\\entity\\mongodb\\");
    }
}
