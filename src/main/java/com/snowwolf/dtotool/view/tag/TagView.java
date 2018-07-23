package com.snowwolf.dtotool.view.tag;

import lombok.Data;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/23 8:11
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class TagView {
    private List<TagView> defualtEntityList;
    private List<TagView> defualtColumnList;
    private List<TagView> customEntityList;
    private List<TagView> customColumnList;
}
