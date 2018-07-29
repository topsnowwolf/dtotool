package com.snowwolf.dtotool.mode;

import lombok.Data;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 22:49
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class ParamVo {
    private boolean notNull;
    private String name;
    private String desc;
    private String type;
}
