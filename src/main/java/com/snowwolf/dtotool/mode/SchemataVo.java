package com.snowwolf.dtotool.mode;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: topsnowwolf
 * @description: 数据库对象
 * @date: Create in 2018/7/21 17:45
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class SchemataVo implements Serializable {
    private String catalogName;
    private String schemaName;
    private String defaultCharacterSetName;
    private String defaultCollationName;
    private String sqlPath;
}
