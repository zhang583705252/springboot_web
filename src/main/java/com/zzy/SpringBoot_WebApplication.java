package com.zzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzy.dao")
public class SpringBoot_WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot_WebApplication.class,args);
    }
    }

