package com.snowwolf.dtotool.view;

import com.snowwolf.dtotool.mode.SchemataVo;
import lombok.Data;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 18:41
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class SchemataView {
    private String code;
    private String msg;
    private List<SchemataVo> list;
}
