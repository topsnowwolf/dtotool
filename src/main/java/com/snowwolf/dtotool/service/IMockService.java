package com.snowwolf.dtotool.service;

import com.snowwolf.dtotool.dto.MockDto;
import com.snowwolf.dtotool.mode.MockVo;
import com.snowwolf.dtotool.mode.ProjectVo;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 20:16
 * @modified by:
 * @versions：0.1.0
 */
public interface IMockService {
    String getQueryParams(MockDto mockDto);
    String getRespont(MockDto mockDto);
    List<MockVo> findMokcs(MockDto mockDto);
    MockVo findOne(MockDto mockDto);
    List<ProjectVo> findProject();
}
