package com.snowwolf.dtotool.yml;

import com.snowwolf.dtotool.view.SchemataView;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/22 12:25
 * @modified by:
 * @versions：0.1.0
 */
@Component
@ConfigurationProperties(prefix = "default.annotation")
@Data
public class GetDefaultAnnotion {
    private String entity;
    private String table;
    private String colummn;
}
