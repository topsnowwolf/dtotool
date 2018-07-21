package com.snowwolf.dtotool.mapper.schemata;

import com.snowwolf.dtotool.mode.SchemataVo;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 18:36
 * @modified by:
 * @versions：0.1.0
 */
public interface SchemataMapper {
    List<SchemataVo> getAll();
    SchemataVo getOne(String schemaName);
}
