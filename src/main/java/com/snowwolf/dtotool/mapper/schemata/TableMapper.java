package com.snowwolf.dtotool.mapper.schemata;

import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.mode.TableVo;
import com.snowwolf.dtotool.view.TableInfoView;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 17:57
 * @modified by:
 * @versions：0.1.0
 */
public interface TableMapper {
    List<TableVo> findTableByDB(String dbName);
    TableVo findTableById(TableReq tableReq);
}
