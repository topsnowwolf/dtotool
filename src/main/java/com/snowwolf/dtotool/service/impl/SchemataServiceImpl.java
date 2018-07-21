package com.snowwolf.dtotool.service.impl;

import com.snowwolf.dtotool.mapper.schemata.SchemataMapper;
import com.snowwolf.dtotool.mode.SchemataVo;
import com.snowwolf.dtotool.service.ISchemataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 20:02
 * @modified by:
 * @versions：0.1.0
 */
@Service
public class SchemataServiceImpl implements ISchemataService{
    @Resource
    private SchemataMapper schemataMapper;
    @Override
    public List<SchemataVo> getAll() {
        return schemataMapper.getAll();
    }

    @Override
    public SchemataVo getOne(String schemaName) {
        return schemataMapper.getOne(schemaName);
    }
}
