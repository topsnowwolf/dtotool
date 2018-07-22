package com.snowwolf.dtotool.view;

import lombok.Data;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 22:59
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class TableView {
    private String dbName;
    private List<TableInfoView> list;
}
