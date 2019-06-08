package com.wlgdo.avatar.service;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.wlgdo.avatar.service.*.mapper")
public class AvatarServiceDSApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvatarServiceDSApplication.class, args);
    }

}
