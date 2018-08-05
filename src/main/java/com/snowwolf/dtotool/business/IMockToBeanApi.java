package com.snowwolf.dtotool.business;

import com.snowwolf.dtotool.dto.MockDto;
import com.snowwolf.dtotool.dto.MockReqDto;
import com.snowwolf.dtotool.mode.MockVo;
import com.snowwolf.dtotool.mode.ProjectVo;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/8/4 9:33
 * @modified by:
 * @versions：0.1.0
 */
public interface IMockToBeanApi {
    String createBean(MockReqDto mockReqDto);
    String createBeanOut(MockReqDto mockReqDto);
    List<MockVo> findMokcs(MockReqDto mockReqDto);
    MockVo findOne(MockReqDto mockReqDto);
    List<ProjectVo> findProject();
}
