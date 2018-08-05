package com.snowwolf.dtotool.business.impl;

import com.alibaba.fastjson.JSONObject;
import com.snowwolf.dtotool.business.IMockToBeanApi;
import com.snowwolf.dtotool.dto.MockDto;
import com.snowwolf.dtotool.dto.MockReqDto;
import com.snowwolf.dtotool.mode.MockVo;
import com.snowwolf.dtotool.mode.ParamVo;
import com.snowwolf.dtotool.mode.ProjectVo;
import com.snowwolf.dtotool.service.IMockService;
import com.snowwolf.dtotool.tool.BeanUtil;
import com.snowwolf.dtotool.tool.ParamsInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/8/4 9:34
 * @modified by:
 * @versions：0.1.0
 */
@Slf4j
@Service
public class MockToBeanApiImpl implements IMockToBeanApi{
    @Resource
    private IMockService mockService;

    @Override
    public String createBean(MockReqDto mockReqDto) {
        log.info("mockReqDto={}",mockReqDto.toString());
        MockDto mockDto = new MockDto();
        BeanUtils.copyProperties(mockReqDto,mockDto);
        String params = mockService.getQueryParams(mockDto);
        List<ParamVo> list = JSONObject.parseArray(params, ParamVo.class);
        list.forEach(praramVo -> log.info(praramVo.getName()));
        ParamsInfo paramsInfo = new ParamsInfo();
        BeanUtils.copyProperties(mockReqDto,paramsInfo);
        paramsInfo.setList(list);
        return BeanUtil.createBeanForMockReqParams(paramsInfo);
    }

    @Override
    public String createBeanOut(MockReqDto mockReqDto) {
        MockDto mockDto = new MockDto();
        BeanUtils.copyProperties(mockReqDto,mockDto);
        String respont = mockService.getRespont(mockDto);
        return BeanUtil.createBeanForMockeRes();
    }

    @Override
    public List<MockVo> findMokcs(MockReqDto mockReqDto) {
        MockDto mockDto = new MockDto();
        BeanUtils.copyProperties(mockReqDto,mockDto);
        return mockService.findMokcs(mockDto);
    }

    @Override
    public MockVo findOne(MockReqDto mockReqDto) {
        MockDto mockDto = new MockDto();
        BeanUtils.copyProperties(mockReqDto,mockDto);
        return mockService.findOne(mockDto);
    }

    @Override
    public List<ProjectVo> findProject() {
        return mockService.findProject();
    }
}
