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
    private List<TagInfoView> defualtEntityList;
    private List<TagInfoView> defualtColumnList;
    private List<TagInfoView> customEntityList;
    private List<TagInfoView> customColumnList;
}
