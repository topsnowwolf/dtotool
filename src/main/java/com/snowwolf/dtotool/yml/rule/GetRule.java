package com.snowwolf.dtotool.yml.rule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * @author: topsnowwolf
 * @description: 静态注入配置信息
 * @date: Create in 2018/8/4 10:17
 * @modified by:
 * @versions：0.1.0
 */
@Configuration
@ConfigurationProperties(prefix = "beanrule",ignoreUnknownFields = false)
@PropertySource(value ={"classpath:rule/namingRule.properties"},ignoreResourceNotFound = true)
public class GetRule {

    public static String customEntityName;

    public static String customEntityImport;

    public static Map<String,String> customEntityExtends;

    public static Map<String,String> customEntityImpl;

    @Value("#{'${exclusiveProperty}'.split(',')}")
    public static List<String> exclusiveProperty;

    public static String getCustomEntityName() {
        return customEntityName;
    }
    public  void setCustomEntityName(String customEntityName) {
        GetRule.customEntityName = customEntityName;
    }

    public  void setCustomEntityImport(String customEntityImport) {
        GetRule.customEntityImport = customEntityImport;
    }

    public  void setCustomEntityExtends(Map<String, String> customEntityExtends) {
        GetRule.customEntityExtends = customEntityExtends;
    }

    public void setCustomEntityImpl(Map<String, String> customEntityImpl) {
        GetRule.customEntityImpl = customEntityImpl;
    }

    public void setExclusiveProperty(List<String> exclusiveProperty) {
        GetRule.exclusiveProperty = exclusiveProperty;
    }
}
