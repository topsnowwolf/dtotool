package com.snowwolf.dtotool.mapper.mock;

import com.snowwolf.dtotool.dto.MockDto;
import com.snowwolf.dtotool.mode.MockVo;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 20:21
 * @modified by:
 * @versions：0.1.0
 */
public interface MockMapper {
    List<MockVo> findMokcs(MockDto mockDto);
    MockVo findOne(MockDto mockDto);
}
