package com.snowwolf.dtotool.service;

import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.mode.TableVo;
import com.snowwolf.dtotool.view.TableInfoView;
import com.snowwolf.dtotool.view.TableView;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 22:46
 * @modified by:
 * @versions：0.1.0
 */
public interface ITableService {
    TableView findTableByDB(String dbName);
    TableInfoView findTableById(TableReq tableReq);
}
