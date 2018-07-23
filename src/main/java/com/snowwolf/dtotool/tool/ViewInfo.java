package com.snowwolf.dtotool.tool;

import com.snowwolf.dtotool.view.ColumView;
import lombok.Data;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/23 22:58
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class ViewInfo {
    private ColumView columView;
    private String className;
    private String packageName;
    private String path;
    private TagVo tagVo;
}
