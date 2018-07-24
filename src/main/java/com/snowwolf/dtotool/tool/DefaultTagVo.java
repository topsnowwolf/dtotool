package com.snowwolf.dtotool.tool;

import lombok.Data;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/24 7:59
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class DefaultTagVo {
    private List<TagInfo> defaultEntityTag;
    private List<TagInfo> defaultPropertyTag;
}
