package com.snowwolf.dtotool.mode;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: topsnowwolf
 * @description: 数据库表结构信息
 * @date: Create in 2018/7/21 17:43
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class TableVo implements Serializable {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableType;
    private String engine;
    private Long version;
    private String rowFormat;
    private Long tableRows;
    private Long avgRowLength;
    private Long dataLength;
    private Long maxDateLength;
    private Long indexLength;
    private Long dataFree;
    private Long autoIncrement;
    private Date createTime;
    private Date updateTime;
    private Date checkTime;
    private String tableCollation;
    private Long checkSum;
    private String createOptions;
    private String tableComment;
}
