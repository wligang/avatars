package com.wlgdo.avatar.service;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

import org.mybatis.spring.annotation.MapperScan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
// 开启dubbo的自动配置
@EnableDubboConfiguration
@MapperScan("com.wlgdo.avatar.service.dao")
public class AvatarServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvatarServiceApplication.class, args);
    }

}
