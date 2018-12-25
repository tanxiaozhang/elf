package com.wux.rcb.elf.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.wux.rcb.elf.biz.dao.info", sqlSessionTemplateRef = "infoSqlSessionTemplate")
public class DataSourceInfoConfig {

    @Bean(name = "infoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.info")
    public DataSource infoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "infoSqlSessionFactory")
    public SqlSessionFactory infoSqlSessionFactory(@Qualifier("infoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/info/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "infoTransactionManager")
    public DataSourceTransactionManager infoTransactionManager(@Qualifier("infoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "infoSqlSessionTemplate")
    public SqlSessionTemplate infoSqlSessionTemplate(@Qualifier("infoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
