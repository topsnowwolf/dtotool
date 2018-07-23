package com.snowwolf.dtotool.tool;

import lombok.Data;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/23 23:02
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class TagVo {
    private List<TagInfo> entityTag;
    private List<TagInfo> propertyTag;
}
