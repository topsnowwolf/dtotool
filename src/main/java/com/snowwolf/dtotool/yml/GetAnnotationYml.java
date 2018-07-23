package com.snowwolf.dtotool.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/22 21:46
 * @modified by:
 * @versions：0.1.0
 */
@Configuration
@ConfigurationProperties(prefix = "tag",ignoreUnknownFields = false)
@PropertySource(value ={"classpath:annotation/annotation.properties",
        "classpath:annotation/custom_entity.properties",
        "classpath:annotation/custom_property.properties"},
        ignoreResourceNotFound = true)
@Data
public class GetAnnotationYml {
    private Map<String,String> defaultEntityMap;
    private Map<String,String> defaultPropertyMap;
    private Map<String,String> customEntityMap;
    private Map<String,String> customPropertyMap;
}
