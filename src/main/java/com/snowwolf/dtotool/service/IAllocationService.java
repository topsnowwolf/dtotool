package com.snowwolf.dtotool.service;

import com.snowwolf.dtotool.view.tag.TagView;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/23 8:07
 * @modified by:
 * @versions：0.1.0
 */
public interface IAllocationService {
    TagView findAll();
    TagView findTag(String type);
}
