package com.snowwolf.dtotool.view;

import lombok.Data;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 23:22
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class ColumInfoView {
    private String columnName;
    private String position;
    private String defaultValue;
    private String ableNull;
    private String dataType;
    private Long maxCharver;
    private Long minCharver;
    /**
     * 精确度
     */
    private Long numberPrecision;

    private Long numberScale;
    /**
     * 精确度
     */
    private Long TimePrecision;
    /**
     * 键值类型
     */
    private String columnKey;
    /**
     * 字段描述
     */
    private String columnComment;
}
