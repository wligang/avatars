package com.wlgdo.avatar.web;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class AvatarWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvatarWebApplication.class, args);
    }

}
