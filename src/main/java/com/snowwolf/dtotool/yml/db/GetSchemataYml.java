package com.snowwolf.dtotool.yml.db;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 21:08
 * @modified by:
 * @versions：0.1.0
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.schemata")
@Data
public class GetSchemataYml {
    private String driverClassName;
    private String username;
    private String password;
    private String url;
}
