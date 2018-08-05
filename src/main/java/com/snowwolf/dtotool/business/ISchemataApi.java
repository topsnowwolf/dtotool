package com.snowwolf.dtotool.business;

import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.dto.TagReq;
import com.snowwolf.dtotool.view.ColumView;
import com.snowwolf.dtotool.view.SchemataView;
import com.snowwolf.dtotool.view.TableView;
import com.snowwolf.dtotool.view.tag.AnnotationView;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/8/4 9:16
 * @modified by:
 * @versions：0.1.0
 */
public interface ISchemataApi {
    SchemataView getAll();
    TableView findTableByDB(String dbName);
    ColumView findColumByTB(TableReq tableReq);
    String createBean(TagReq tagReq);
    AnnotationView findAnnotation();
}
