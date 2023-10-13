package com.photon.demo.config.database;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author liuzaoxin
 * @description
 * @date 2023/08/07/ 18:29
 */
@Configuration
@MapperScan(basePackages = "com.photon.demo.mapper.first", sqlSessionTemplateRef = "sqlSessionTemplateFirst")
public class DatabaseConfigFirst {
    @Bean(name = "first")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactoryFirst")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("first") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean ();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/first/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "transactionManagerFirst")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("first") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplateFirst")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryFirst") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
