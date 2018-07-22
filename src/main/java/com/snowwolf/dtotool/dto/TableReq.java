package com.snowwolf.dtotool.dto;

import lombok.Data;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 23:16
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class TableReq {
    private String dbName;
    private String tableName;
}
