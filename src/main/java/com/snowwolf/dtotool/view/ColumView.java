package com.snowwolf.dtotool.view;

import lombok.Data;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 23:21
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class ColumView {
    private String tableName;
    private String dbName;
    private String tableDesc;
    private List<ColumInfoView> list;
}
