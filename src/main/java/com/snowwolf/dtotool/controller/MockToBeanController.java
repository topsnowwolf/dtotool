package com.snowwolf.dtotool.controller;

import com.alibaba.fastjson.JSONObject;
import com.snowwolf.dtotool.dto.MockDto;
import com.snowwolf.dtotool.dto.MockReqDto;
import com.snowwolf.dtotool.mapper.mock.MockMapper;
import com.snowwolf.dtotool.mode.ParamVo;
import com.snowwolf.dtotool.service.IMockService;
import com.snowwolf.dtotool.tool.BeanUtil;
import com.snowwolf.dtotool.tool.ParamsInfo;
import com.snowwolf.dtotool.view.DataVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
    @Resource
    private IMockService mockService;

    @GetMapping("/project")
    public DataVo findProject(){
        return new DataVo("0000","SUCCESS",mockService.findProject());
    }

    @GetMapping("/find")
    public DataVo findMocks(MockReqDto mockReqDto){
        MockDto mockDto = new MockDto();
        BeanUtils.copyProperties(mockReqDto,mockDto);
        return new DataVo("0000","SUCCESS",mockService.findMokcs(mockDto));
    }
    @GetMapping("/findOne")
    public DataVo findMock(MockReqDto mockReqDto){
        MockDto mockDto = new MockDto();
        BeanUtils.copyProperties(mockReqDto,mockDto);
        return new DataVo("0000","SUCCESS",mockService.findOne(mockDto));
    }
    @PostMapping("/increate")
    public DataVo createBean(@RequestBody  MockReqDto mockReqDto){
        log.info("mockReqDto={}",mockReqDto.toString());
        MockDto mockDto = new MockDto();
        BeanUtils.copyProperties(mockReqDto,mockDto);
        String params = mockService.getQueryParams(mockDto);
        List<ParamVo> list = JSONObject.parseArray(params, ParamVo.class);
        list.forEach(praramVo -> log.info(praramVo.getName()));
        ParamsInfo paramsInfo = new ParamsInfo();
        BeanUtils.copyProperties(mockReqDto,paramsInfo);
        paramsInfo.setList(list);
        String url = BeanUtil.createBeanForMockReqParams(paramsInfo);
        return new DataVo("0000","SUCCESS",url);
    }

    @PostMapping("/outcreate")
    public DataVo createBeanOut(@RequestBody MockReqDto mockReqDto){
        MockDto mockDto = new MockDto();
        BeanUtils.copyProperties(mockReqDto,mockDto);
        String respont = mockService.getRespont(mockDto);
        String url = BeanUtil.createBeanForMockeRes();
        return new DataVo("0000","SUCCESS",url);
    }
}

