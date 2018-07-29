package com.snowwolf.dtotool.mode;

import lombok.Data;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 20:21
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class MockVo {
    private Integer id;
    private Integer projectId;
    private Integer groupId;
    private String name;
    private Integer status;
    private String requestUrl;
    private String requestMethod;
    private String requestHeaders;
    private String quereyParams;
    private String successType;
    private String successExample;
    private String remark;



}
