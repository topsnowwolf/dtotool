package com.snowwolf.dtotool.dto;

import lombok.Data;

import java.util.List;

/**
 * @Auther: mo.zongzhe
 * @Date: 2018/7/24 10:01
 * @Description:
 * @since 0.1.0
 */
@Data
public class TagReq {
    /**
     * 必传
     */
    private String dbName;
    /**
     * 必传
     */
    private String tableName;
    private String className;
    private String packageName;
    /**
     * 必传
     */
    private String path;
    /**
     * 必传
     */
    private List<String> entityList;
    /**
     * 必传
     */
    private List<String> propertyList;
}
