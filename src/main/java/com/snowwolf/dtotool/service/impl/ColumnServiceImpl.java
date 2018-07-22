package com.snowwolf.dtotool.service.impl;

import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.mapper.schemata.ColumnMapper;
import com.snowwolf.dtotool.mode.ColumnVo;
import com.snowwolf.dtotool.service.IColumnService;
import com.snowwolf.dtotool.view.ColumInfoView;
import com.snowwolf.dtotool.view.ColumView;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 23:11
 * @modified by:
 * @versions：0.1.0
 */
@Service
public class ColumnServiceImpl implements IColumnService {
    @Resource
    private ColumnMapper columnMapper;
    @Override
    public ColumView findTableByTB(TableReq tableReq) {
        ColumView columView = new ColumView();
        columView.setDbName(tableReq.getDbName());
        columView.setTableName(tableReq.getTableName());
        List<ColumInfoView> list = new ArrayList<>();
        columnMapper.findTableByTB(tableReq).forEach(columnVo -> {
            ColumInfoView columInfoView = new ColumInfoView();
            columInfoView.setColumnName(columnVo.getColumnName());
            columInfoView.setAbleNull(columnVo.getIsNullable());
            columInfoView.setColumnComment(columnVo.getColumnComment());
            columInfoView.setColumnKey(columnVo.getColumnKey());
            columInfoView.setDataType(columnVo.getDataType());
            columInfoView.setDefaultValue(columnVo.getColumnDefault());
            columInfoView.setMaxCharver(columnVo.getCharacterMaximumLength());
            columInfoView.setNumberPrecision(columnVo.getNumbericPrecision());
            columInfoView.setTimePrecision(columnVo.getDatetimePrecision());
            list.add(columInfoView);
        });
        columView.setList(list);
        return columView;
    }
}
