package com.snowwolf.dtotool.mapper.schemata;

import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.mode.ColumnVo;
import com.snowwolf.dtotool.mode.TableVo;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 18:36
 * @modified by:
 * @versions：0.1.0
 */
public interface ColumnMapper {
    List<ColumnVo> findTableByTB(TableReq tableReq);
}
