package com.snowwolf.dtotool.controller;

import com.alibaba.fastjson.JSONObject;
import com.snowwolf.dtotool.business.IMockToBeanApi;
import com.snowwolf.dtotool.dto.MockDto;
import com.snowwolf.dtotool.dto.MockReqDto;
import com.snowwolf.dtotool.mapper.mock.MockMapper;
import com.snowwolf.dtotool.mode.ParamVo;
import com.snowwolf.dtotool.service.IMockService;
import com.snowwolf.dtotool.tool.BeanUtil;
import com.snowwolf.dtotool.tool.ParamsInfo;
import com.snowwolf.dtotool.view.DataVo;
import com.snowwolf.dtotool.yml.rule.GetRule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 20:10
 * @modified by:
 * @versions：0.1.0
 */
@Slf4j
@RestController
@RequestMapping("/mock")
public class MockToBeanController {
    private final static Logger logger = LoggerFactory.getLogger(MockToBeanController.class);
    @Autowired
    private IMockToBeanApi mockToBeanApi;

    @GetMapping("/project")
    public DataVo findProject(){
        return new DataVo("0000","SUCCESS",mockToBeanApi.findProject());
    }

    @GetMapping("/find")
    public DataVo findMocks(MockReqDto mockReqDto){
        return new DataVo("0000","SUCCESS",mockToBeanApi.findMokcs(mockReqDto));
    }
    @GetMapping("/findOne")
    public DataVo findMock(MockReqDto mockReqDto){
        return new DataVo("0000","SUCCESS",mockToBeanApi.findOne(mockReqDto));
    }
    @PostMapping("/increate")
    public DataVo createBean(@RequestBody  MockReqDto mockReqDto){
        String url = mockToBeanApi.createBean(mockReqDto);
        return new DataVo("0000","SUCCESS",url);
    }

    @PostMapping("/outcreate")
    public DataVo createBeanOut(@RequestBody MockReqDto mockReqDto){
        String url = mockToBeanApi.createBeanOut(mockReqDto);
        return new DataVo("0000","SUCCESS",url);
    }
}

