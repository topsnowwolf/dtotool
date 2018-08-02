package com.snowwolf.dtotool.dto;

import lombok.Data;

import java.util.List;

/**
 * @Auther: mo.zongzhe
 * @Date: 2018/8/2 18:53
 * @Description:
 * @since 0.1.0
 */
@Data
public class JsonReqDto {
    private String dbName;
    private String tableName;
    private List<String> colums;
    private String type;
}
