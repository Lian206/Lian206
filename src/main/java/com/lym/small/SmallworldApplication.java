package com.lym.small;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.lym.small.mapper")
public class SmallworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmallworldApplication.class, args);
    }

}
