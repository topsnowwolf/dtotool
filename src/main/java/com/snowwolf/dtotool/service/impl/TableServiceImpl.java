package com.snowwolf.dtotool.service.impl;

import com.snowwolf.dtotool.mapper.schemata.TableMapper;
import com.snowwolf.dtotool.mode.TableVo;
import com.snowwolf.dtotool.service.ITableService;
import com.snowwolf.dtotool.view.TableInfoView;
import com.snowwolf.dtotool.view.TableView;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 22:47
 * @modified by:
 * @versions：0.1.0
 */
@Service
public class TableServiceImpl implements ITableService {
    @Resource
    private TableMapper tableMapper;
    @Override
    public TableView findTableByDB(String dbName) {
        TableView tableView = new TableView();
        tableView.setDbName(dbName);
        List<TableInfoView> list = new ArrayList<>();
        tableMapper.findTableByDB(dbName).forEach(tableVo -> {
            TableInfoView tableInfoView = new TableInfoView();
            tableInfoView.setTableDesc(tableVo.getTableComment());
            tableInfoView.setTableName(tableVo.getTableName());
            list.add(tableInfoView);
        });
        tableView.setList(list);
        return tableView;
    }
}
