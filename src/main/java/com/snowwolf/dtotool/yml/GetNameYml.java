package com.snowwolf.dtotool.yml;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: topsnowwolf
 * @description: 静态属性注入，必须使用value
 * @date: Create in 2018/7/22 17:02
 * @modified by:
 * @versions：0.1.0
 */
@Component
@ConfigurationProperties(prefix = "name.rule")
public class GetNameYml {
    private static  String entity;
    private static  String url;

    public static String getEntity() {
        return entity;
    }

    @Value("${name.rule.entity}")
    public void setEntity(String entity) {
        GetNameYml.entity = entity;
    }

    public static String getUrl() {
        return url;
    }

    @Value("${name.rule.url}")
    public void setUrl(String url) {
        GetNameYml.url = url;
    }
}
