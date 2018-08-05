package com.snowwolf.dtotool.service.impl;

import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.dto.TagReq;
import com.snowwolf.dtotool.mapper.schemata.ColumnMapper;
import com.snowwolf.dtotool.mapper.schemata.TableMapper;
import com.snowwolf.dtotool.mode.TableVo;
import com.snowwolf.dtotool.service.IAllocationService;
import com.snowwolf.dtotool.service.IColumnService;
import com.snowwolf.dtotool.tool.TagInfo;
import com.snowwolf.dtotool.tool.TagVo;
import com.snowwolf.dtotool.tool.ViewInfo;
import com.snowwolf.dtotool.view.ColumInfoView;
import com.snowwolf.dtotool.view.ColumView;
import com.snowwolf.dtotool.view.tag.AnnotationView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 23:11
 * @modified by:
 * @versions：0.1.0
 */
@Slf4j
@Service
public class ColumnServiceImpl implements IColumnService {
    @Resource
    private TableMapper tableMapper;
    @Resource
    private ColumnMapper columnMapper;
    @Resource
    private IAllocationService allocationService;
    @Override
    public ColumView findTableByTB(TableReq tableReq) {
        TableVo tableVo = tableMapper.findTableById(tableReq);
        ColumView columView = new ColumView();
        columView.setDbName(tableReq.getDbName());
        columView.setTableName(tableVo.getTableName());
        columView.setTableDesc(tableVo.getTableComment());
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
