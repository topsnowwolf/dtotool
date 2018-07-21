package com.snowwolf.dtotool.mode;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: topsnowwolf
 * @description: 字段对象
 * @date: Create in 2018/7/21 17:47
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class ColumnVo implements Serializable {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String columnName;
    private Long ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private Long characterMaximumLength;
    private Long characterOctetLength;
    private Long numbericPrecision;
    private Long numbericScale;
    private Long datetimePrecision;
    private String characterSetName;
    private String collationName;
    private String columnType;
    private String columnKey;
    private String extra;
    private String privileges;
    private String columnComment;
    private String generationExpression;

}
