package com.example.dynamicdatasource.dataSourceTest;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.example.dynamicdatasource.dao", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    /**
     * 构建自定义的动态数据源DataSource
     * @return
     */
//    @Bean(name = "dynamicDataSource")
////    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource(){
//        return new DynamicDataSourceOfDruid();
//    }


    @Bean(name = "dynamicDataSource")
    public Ds2DynamicDataSource dataSource(){
        return Ds2DynamicDataSource.getInstance();
    }

    /**
     * 这是使用springboot的默认的tomcat连接池，也可以用
     * @param dataSource
     * @return
     */
//    @Bean(name = "dynamicDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource(){
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.type(DynamicDataSource.class);
//        dataSourceBuilder.url(dataSourceProperty.getUrl());
//        dataSourceBuilder.username(dataSourceProperty.getUsername());
//        dataSourceBuilder.password(dataSourceProperty.getPassword());
//        dataSourceBuilder.driverClassName(dataSourceProperty.getDriverClassName());
//        dataSourceBuilder.type(DynamicDataSource.class);
//        DataSource build = dataSourceBuilder.build();
//        return dataSourceBuilder.build();
//    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath:mapping/*.xml");
            sqlSessionFactoryBean.setMapperLocations(resources);
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
