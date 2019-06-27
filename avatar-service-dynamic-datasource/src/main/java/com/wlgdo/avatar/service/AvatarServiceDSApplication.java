package com.wlgdo.avatar.service;


import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@MapperScan("com.wlgdo.avatar.service.*.mapper")
@ComponentScan("com.wlgdo.avatar.service.quartz")
public class AvatarServiceDSApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvatarServiceDSApplication.class, args);
    }

}
