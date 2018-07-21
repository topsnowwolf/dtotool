package com.snowwolf.dtotool.controller;

import com.snowwolf.dtotool.mapper.schemata.SchemataMapper;
import com.snowwolf.dtotool.mode.SchemataVo;
import com.snowwolf.dtotool.service.ISchemataService;
import com.snowwolf.dtotool.view.SchemataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description: 查询
 * @date: Create in 2018/7/21 18:43
 * @modified by:
 * @versions：0.1.0
 */
@RestController
@RequestMapping("/db")
public class SchemataController {
    @Autowired
    private ISchemataService schemataService;

    @GetMapping("/all")
    public SchemataView findAll(){
        SchemataView schemataView = new SchemataView();
        List<SchemataVo> list = schemataService.getAll();
        schemataView.setList(list);
        schemataView.setCode("0000");
        schemataView.setMsg("SUCCESS");
        return schemataView;
    }

}
