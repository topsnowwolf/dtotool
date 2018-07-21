package com.snowwolf.dtotool.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 21:04
 * @modified by:
 * @versions：0.1.0
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.mock")
@Data
public class GetMockYml {
    private String driverClassName;
    private String username;
    private String password;
    private String url;
}
