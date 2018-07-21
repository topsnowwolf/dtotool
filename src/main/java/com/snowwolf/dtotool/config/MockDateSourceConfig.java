package com.snowwolf.dtotool.config;

import com.snowwolf.dtotool.yml.GetMapperYml;
import com.snowwolf.dtotool.yml.GetMockYml;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author: topsnowwolf
 * @description: 访问mock保存的数据库的数据源
 * @date: Create in 2018/7/21 17:43
 * @modified by:
 * @versions：0.1.0
 */
@Configuration
@MapperScan(basePackages = "com.snowwolf.dtotool.mapper.mock", sqlSessionTemplateRef  = "mockSqlSessionTemplate")
public class MockDateSourceConfig {
    @Resource
    private GetMockYml getMockYml;
    @Resource
    private GetMapperYml getMapperYml;

    @Bean(name = "mockDataSource")
    public DataSource mockDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getMockYml.getDriverClassName());
        dataSource.setUrl(getMockYml.getUrl());
        dataSource.setUsername(getMockYml.getUsername());
        dataSource.setPassword(getMockYml.getPassword());
        return dataSource;
    }

    @Bean(name = "mockSqlSessionFactory")
    public SqlSessionFactory mockSqlSessionFactory(@Qualifier("mockDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(getMapperYml.getMock()));
        return bean.getObject();
    }

    @Bean(name = "mockTransactionManager")
    public DataSourceTransactionManager mockTransactionManager(@Qualifier("mockDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mockSqlSessionTemplate")
    public SqlSessionTemplate mockSqlSessionTemplate(@Qualifier("mockSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
