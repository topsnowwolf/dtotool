package com.snowwolf.dtotool.controller;

import com.snowwolf.dtotool.service.IAllocationService;
import com.snowwolf.dtotool.view.tag.TagView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/23 8:05
 * @modified by:
 * @versions：0.1.0
 */
@Slf4j
@RestController
@RequestMapping("/tag")
public class AllocationController {

    @Autowired
    private IAllocationService allocationService;

    @GetMapping("/findAll")
    public TagView findTagAll(){
        return allocationService.findAll();
    }

    @GetMapping("/type")
    public TagView findTagByType(String type){
        log.info("type={}",type);
        return allocationService.findTag(type);
    }
}
