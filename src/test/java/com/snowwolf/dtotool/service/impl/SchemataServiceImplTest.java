package com.snowwolf.dtotool.service.impl;

import com.snowwolf.dtotool.mapper.schemata.SchemataMapper;
import com.snowwolf.dtotool.mode.SchemataVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: topsnowwolf
 * @description: 单元测试类
 * @date: Create in 2018/7/21 21:30
 * @modified by:
 * @versions：0.1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SchemataServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(SchemataServiceImplTest.class);
    @Resource
    private SchemataMapper schemataMapper;
    @Test
    public void getAll() throws Exception {
        List<SchemataVo> list = schemataMapper.getAll();
        logger.info("size={}",list.size());
    }

    @Test
    public void getOne() throws Exception {
        SchemataVo schemataVo = schemataMapper.getOne("234234");
    }

}