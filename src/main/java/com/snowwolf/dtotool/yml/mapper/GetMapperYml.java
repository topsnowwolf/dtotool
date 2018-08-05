package com.snowwolf.dtotool.yml.mapper;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/21 21:17
 * @modified by:
 * @versions：0.1.0
 */
@Component
@ConfigurationProperties(prefix = "mybatis.mapper")
@Data
public class GetMapperYml {
    private String mock;
    private String schemata;
}
