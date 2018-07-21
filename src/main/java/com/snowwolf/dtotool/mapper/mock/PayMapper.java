package com.snowwolf.dtotool.mapper.mock;

import com.snowwolf.dtotool.mode.PayVo;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 18:49
 * @modified by:
 * @versions：0.1.0
 */
public interface PayMapper {
    List<PayVo> getAll();
    PayVo getOne(String payId);
}

