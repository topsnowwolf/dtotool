package com.snowwolf.dtotool.config;

import com.snowwolf.dtotool.yml.GetMapperYml;
import com.snowwolf.dtotool.yml.GetMockYml;
import com.snowwolf.dtotool.yml.GetSchemataYml;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author: topsnowwolf
 * @description: 访问mysql，底层基本数据库的数据源。information_schema
 * @date: Create in 2018/7/21 17:43
 * @modified by:
 * @versions：0.1.0
 */
@Configuration
@MapperScan(basePackages = "com.snowwolf.dtotool.mapper.schemata", sqlSessionTemplateRef  = "schemataSqlSessionTemplate")
public class SchemataDataSourceConfig {
    @Resource
    private GetSchemataYml getSchemataYml;
    @Resource
    private GetMapperYml getMapperYml;

    @Bean(name = "schemataDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.schemata")
    @Primary
    public DataSource schemataDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getSchemataYml.getDriverClassName());
        dataSource.setUrl(getSchemataYml.getUrl());
        dataSource.setUsername(getSchemataYml.getUsername());
        dataSource.setPassword(getSchemataYml.getPassword());
        return dataSource;
    }

    @Bean(name = "schemataSqlSessionFactory")
    @Primary
    public SqlSessionFactory schemataSqlSessionFactory(@Qualifier("schemataDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(getMapperYml.getSchemata()));
        return bean.getObject();
    }

    @Bean(name = "schemataTransactionManager")
    @Primary
    public DataSourceTransactionManager schemataTransactionManager(@Qualifier("schemataDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "schemataSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate schemataSqlSessionTemplate(@Qualifier("schemataSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
