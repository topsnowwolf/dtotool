package com.snowwolf.dtotool.dto;

import lombok.Data;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 20:17
 * @modified by:
 * @versions：0.1.0
 */
@Data
public class MockDto {
    private String url;
    private Integer id;
    private String name;
    private String projectId;

    @Override
    public String toString() {
        return "MockDto{" +
                "url='" + url + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
