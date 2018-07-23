package com.snowwolf.dtotool.service.impl;

import com.snowwolf.dtotool.service.IAllocationService;
import com.snowwolf.dtotool.view.tag.TagView;
import com.snowwolf.dtotool.yml.GetAnnotationYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/23 8:07
 * @modified by:
 * @versions：0.1.0
 */
@Slf4j
@Service
public class AllocationServiceImpl implements IAllocationService {
    private final String defaultEntityMap = "0";
    private final String defaultPropertyMap = "1";
    private final String customEntityMap = "2";
    private final String custPropertyMap = "3";
    @Resource
    private GetAnnotationYml getAnnotationYml;
    @Override
    public TagView findAll() {
        Map<String,String> defaultEntityMap = getAnnotationYml.getDefaultEntityMap();
        Map<String,String> defaultPropertyMap = getAnnotationYml.getDefaultPropertyMap();
        Map<String,String> customEntityMap = getAnnotationYml.getCustomEntityMap();
        Map<String,String> custPropertyMap = getAnnotationYml.getCustomPropertyMap();
        return null;
    }

    @Override
    public TagView findTag(String type) {
        if(defaultEntityMap.equals(type)){
            Map<String,String> defaultEntityMap = getAnnotationYml.getDefaultEntityMap();
        }
        if(defaultEntityMap.equals(type)){
            Map<String,String> defaultPropertyMap = getAnnotationYml.getDefaultPropertyMap();
        }
        if(defaultEntityMap.equals(type)){
            Map<String,String> customEntityMap = getAnnotationYml.getCustomEntityMap();
        }
        if(defaultEntityMap.equals(type)){
            Map<String,String> custPropertyMap = getAnnotationYml.getCustomPropertyMap();
        }
        return null;
    }
}
