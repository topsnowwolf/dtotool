package com.snowwolf.dtotool.tool;

import lombok.Data;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/23 23:02
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class TagInfo {
    private String importUrl;
    private String name;
    private Integer type;
    private String organization;
    private String status;
    private Integer tagType;
}
