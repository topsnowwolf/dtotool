package com.snowwolf.dtotool.mapper.schemata;

import com.snowwolf.dtotool.mode.TableVo;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 17:57
 * @modified by:
 * @versions：0.1.0
 */
public interface TableMapper {
    List<TableVo> findTableByDB(String dbName);
}
