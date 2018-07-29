package com.snowwolf.dtotool.tool;

import com.snowwolf.dtotool.mode.ParamVo;
import com.snowwolf.dtotool.view.ColumView;
import lombok.Data;

import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 22:58
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class ParamsInfo {
    private String className;
    private String packageName;
    private String path;
    private List<ParamVo> list;
}
