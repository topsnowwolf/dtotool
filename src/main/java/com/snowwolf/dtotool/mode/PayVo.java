package com.snowwolf.dtotool.mode;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 18:46
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class PayVo implements Serializable {
    private String tradeId;
    private String payId;
    private String state;
    private Long money;
}
