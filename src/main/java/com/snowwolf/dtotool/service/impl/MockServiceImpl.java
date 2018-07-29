package com.snowwolf.dtotool.service.impl;

import com.snowwolf.dtotool.dto.MockDto;
import com.snowwolf.dtotool.mapper.mock.MockMapper;
import com.snowwolf.dtotool.mapper.mock.ProjectMapper;
import com.snowwolf.dtotool.mode.MockVo;
import com.snowwolf.dtotool.mode.ProjectVo;
import com.snowwolf.dtotool.service.IMockService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 20:20
 * @modified by:
 * @versions：0.1.0
 */

@Slf4j
@Service
public class MockServiceImpl implements IMockService {
    private final static Logger logger = LoggerFactory.getLogger(MockServiceImpl.class);
    @Resource
    private MockMapper mockMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Override
    public String getQueryParams(MockDto mockDto) {
        logger.info("mockDto={}",mockDto);
        return mockMapper.findOne(mockDto).getQuereyParams();
    }

    @Override
    public String getRespont(MockDto mockDto) {
        return mockMapper.findOne(mockDto).getSuccessExample();
    }

    @Override
    public List<MockVo> findMokcs(MockDto mockDto) {
        return mockMapper.findMokcs(mockDto);
    }

    @Override
    public MockVo findOne(MockDto mockDto) {
        return mockMapper.findOne(mockDto);
    }

    @Override
    public List<ProjectVo> findProject() {
        return projectMapper.findMokcByPro();
    }
}
