package com.snowwolf.dtotool.service.impl;

import com.snowwolf.dtotool.service.IAllocationService;
import com.snowwolf.dtotool.view.tag.TagInfoView;
import com.snowwolf.dtotool.view.tag.TagView;
import com.snowwolf.dtotool.yml.GetAnnotationYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
        TagView tagView = new TagView();
        List<TagInfoView> defaultEntitylist = new ArrayList<>();
        List<TagInfoView> defaultPropertylist = new ArrayList<>();
        List<TagInfoView> customEntitylist = new ArrayList<>();
        List<TagInfoView> custPropertylist = new ArrayList<>();
        Map<String,String> defaultEntityMap = getAnnotationYml.getDefaultEntityMap();
        defaultEntitylist = setTagInfoView(defaultEntitylist,defaultEntityMap);
        Map<String,String> defaultPropertyMap = getAnnotationYml.getDefaultPropertyMap();
        defaultPropertylist = setTagInfoView(defaultPropertylist,defaultPropertyMap);
        Map<String,String> customEntityMap = getAnnotationYml.getCustomEntityMap();
        customEntitylist = setTagInfoView(customEntitylist,customEntityMap);
        Map<String,String> custPropertyMap = getAnnotationYml.getCustomPropertyMap();
        custPropertylist = setTagInfoView(custPropertylist,custPropertyMap);
        tagView.setCustomColumnList(custPropertylist);
        tagView.setCustomEntityList(customEntitylist);
        tagView.setDefualtColumnList(defaultPropertylist);
        tagView.setDefualtEntityList(defaultEntitylist);
        return tagView;
    }

    @Override
    public TagView findTag(String type) {
        TagView tagView = new TagView();
        List<TagInfoView> list = new ArrayList<>();
        if(defaultEntityMap.equals(type)){
            Map<String,String> defaultEntityMap = getAnnotationYml.getDefaultEntityMap();
            list = setTagInfoView(list,defaultEntityMap);
            tagView.setDefualtEntityList(list);
        }
        if(defaultPropertyMap.equals(type)){
            Map<String,String> defaultPropertyMap = getAnnotationYml.getDefaultPropertyMap();
            list = setTagInfoView(list,defaultPropertyMap);
            tagView.setDefualtColumnList(list);
        }
        if(customEntityMap.equals(type)){
            Map<String,String> customEntityMap = getAnnotationYml.getCustomEntityMap();
            list = setTagInfoView(list,customEntityMap);
            tagView.setCustomEntityList(list);
        }
        if(custPropertyMap.equals(type)){
            Map<String,String> custPropertyMap = getAnnotationYml.getCustomPropertyMap();
            list = setTagInfoView(list,custPropertyMap);
            tagView.setCustomColumnList(list);
        }
        return tagView;
    }

    private List<TagInfoView> setTagInfoView(List<TagInfoView> list,Map<String,String> map){
        if(map != null && !map.isEmpty()){
            map.forEach((k,v)->{
                TagInfoView tagInfoView = new TagInfoView();
                tagInfoView.setImportUrl(v);
                tagInfoView.setName(k);
                list.add(tagInfoView);
            });
        }
        return list;
    }
}
