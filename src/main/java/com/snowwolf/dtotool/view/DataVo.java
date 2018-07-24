package com.snowwolf.dtotool.view;

import lombok.Data;

/**
 * @Auther: mo.zongzhe
 * @Date: 2018/7/24 10:06
 * @Description:
 * @since 0.1.0
 */
@Data
public class DataVo {
    private String code;
    private String msg;
    private Object data;

    public DataVo(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
