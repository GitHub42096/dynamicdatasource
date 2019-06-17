package com.example.dynamicdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.dynamicdatasource"})
@MapperScan("com.example.dynamicdatasource.dao")
public class DynamicdatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicdatasourceApplication.class, args);
    }

}
