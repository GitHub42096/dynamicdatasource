package com.example.dynamicdatasource.dataSourceTest;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zj
 * @version 1.0
 * @description 动态数据源
 * @create 2019/6/11 18:00
 **/
@PropertySource({"classpath:application.properties"})
public class Ds2DynamicDataSource extends AbstractRoutingDataSource {
    private static Ds2DynamicDataSource instance;
    private static byte[] lock = new byte[0];
    private static Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
    @Autowired
    DruidDataSourceProperty druidDataSourceProperty;


    public Ds2DynamicDataSource() {
        Map<Object,Object> dataSourceMap=new HashMap<Object, Object>();

        DruidDataSource wdataSource = new DruidDataSource();
        wdataSource.setDriverClassName("com.mysql.jdbc.Driver");
        wdataSource.setUrl("jdbc:mysql://127.0.0.1:3306/miaosha?useUnicode=true&characterEncoding=utf8");
        wdataSource.setUsername("root");
        wdataSource.setPassword("123456");
        wdataSource.setMaxActive(5);
        wdataSource.setMinIdle(5);
        wdataSource.setInitialSize(5);
        dataSourceMap.put("miaosha", wdataSource);

        DruidDataSource wdataSource1 = new DruidDataSource();
        wdataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        wdataSource1.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8");
        wdataSource1.setUsername("root");
        wdataSource1.setPassword("123456");
        wdataSource1.setMaxActive(5);
        wdataSource1.setMinIdle(5);
        wdataSource1.setInitialSize(5);
        dataSourceMap.put("test", wdataSource1);

        this.setTargetDataSources(dataSourceMap);
    }

    public static synchronized Ds2DynamicDataSource getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Ds2DynamicDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();// 必须添加该句，否则新添加数据源无法识别到
    }

    public Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dbKey = DataSourceContextHolder.getDB();
		/*if (StringUtils.isBlank(dbKey)) {
			dbKey = "read";
		}*/
        return dbKey;
    }

}
