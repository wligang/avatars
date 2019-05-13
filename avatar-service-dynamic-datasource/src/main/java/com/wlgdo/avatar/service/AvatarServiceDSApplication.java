package com.wlgdo.avatar.service;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("com.wlgdo.avatar.service")
public class AvatarServiceDSApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvatarServiceDSApplication.class, args);
    }

}
