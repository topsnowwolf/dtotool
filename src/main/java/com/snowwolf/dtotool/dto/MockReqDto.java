package com.snowwolf.dtotool.dto;

import lombok.Data;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/29 8:58
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class MockReqDto {
    private String projectId;
    private String url;
    private Integer id;
    private String name;
    private String className;
    private String packageName;
    private String path;
}
