package com.wlgdo.avatar.service;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.wlgdo.avatar.service.quartz.config.ApplicatonCloseEventListener;
import com.wlgdo.avatar.service.quartz.config.ApplicatonStartEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


@SpringBootApplication
@MapperScan("com.wlgdo.avatar.service.*.mapper")
public class AvatarDSApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AvatarDSApplication.class);
        application.addListeners(new ApplicatonStartEventListener());
        application.addListeners(new ApplicatonCloseEventListener());
        application.run(args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    @PostConstruct
    public void init() {
        System.out.println("init................");
    }
}
