package com.wlgdo.avatar.service;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.wlgdo.avatar.service.common.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.wlgdo.avatar.service.*.mapper")
@ComponentScan("com.wlgdo.avatar.service")
public class AvatarServiceDSApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvatarServiceDSApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
