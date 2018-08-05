package com.snowwolf.dtotool.service;

import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.dto.TagReq;
import com.snowwolf.dtotool.view.ColumView;
import com.snowwolf.dtotool.view.tag.AnnotationView;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 23:10
 * @modified by:
 * @versions：0.1.0
 */
public interface IColumnService {
    ColumView findTableByTB(TableReq tableReq);
}
