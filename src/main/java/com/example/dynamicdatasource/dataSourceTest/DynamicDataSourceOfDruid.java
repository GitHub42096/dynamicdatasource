//package com.example.dynamicdatasource.dataSourceTest;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import com.alibaba.druid.pool.DruidPooledConnection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import javax.sql.PooledConnection;
//import java.lang.reflect.Field;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Properties;
//
///**
// * Created by meridian on 2018/9/26.
// */
//@Component
//public class DynamicDataSourceOfDruid extends DruidDataSource {
//    public static ThreadLocal<DruidDataSource> connectionThreadLocal = new ThreadLocal<>();
//    public static String flagDB = "";
//    private static HashMap<String, String> dataBaseMap = new HashMap<>();
//
//    static {
//        dataBaseMap.put("1", "miaosha");
//        dataBaseMap.put("2", "test");
//    }
//
//    /**
//     * 数据源中配置注入不进来，因此自己写了一个读取property的类去读取连接配置
//     */
//    @Autowired
//    DruidDataSourceProperty druidDataSourceProperty;
//
//    public static void closeConnection() {
//        DruidDataSource druidDataSource = connectionThreadLocal.get();
//        if (druidDataSource != null) {
//            try {
//                druidDataSource.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//
//    @Override
//    public DruidPooledConnection getConnection() throws SQLException {
//
//        try {
//
//
//
//
//            String db = DataSourceContextHolder.getDB();
//            if (db == null) {
//                db = "1";
//            }
//
////            if (flagDB != null && flagDB.equals(db)){
////                DruidDataSource druidDataSource = connectionThreadLocal.get();
////                if (druidDataSource != null){
////                    return druidDataSource.getConnection();
////                }
////            }else {
////                connectionThreadLocal = new ThreadLocal<>();
////                flagDB = db;
////                closeConnection();
////            }
////
////            if (flagDB == null){
////                flagDB = db;
////            }
//
//            Properties properties = this.getConnectProperties();
//            Field[] declaredFields = druidDataSourceProperty.getClass().getDeclaredFields();
//            for (Field field : declaredFields) {
//                field.setAccessible(true);
//                properties.setProperty(field.getName(), (String) field.get(druidDataSourceProperty));
//            }
//            String urlFormat = properties.getProperty("url");
//            String url = String.format(urlFormat, dataBaseMap.get(db));
//            properties.setProperty("url", url);
//            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
////            DruidPooledConnection druidPooledConnection = (DruidPooledConnection) dataSource.getConnection();
//            DruidPooledConnection connection = (DruidPooledConnection) dataSource.getConnection();
//            connectionThreadLocal.set((DruidDataSource) dataSource);
//            return connection;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
