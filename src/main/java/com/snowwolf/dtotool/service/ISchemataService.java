package com.snowwolf.dtotool.service;

import com.snowwolf.dtotool.mode.SchemataVo;
import com.snowwolf.dtotool.view.SchemataView;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 20:02
 * @modified by:
 * @versions：0.1.0
 */
public interface ISchemataService {
    SchemataView getAll();
    SchemataVo getOne(String schemaName);
}
