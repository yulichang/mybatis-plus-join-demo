package com.example.mpp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mpp.mapper")
public class MppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MppApplication.class, args);
    }

}
