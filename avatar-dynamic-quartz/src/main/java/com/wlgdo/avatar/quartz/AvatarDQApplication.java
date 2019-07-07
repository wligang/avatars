package com.wlgdo.avatar.quartz;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.wlgdo.avatar.quartz.schedule.config.ApplicatonCloseEventListener;
import com.wlgdo.avatar.quartz.schedule.config.ApplicatonStartEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


@SpringBootApplication
@MapperScan("com.wlgdo.avatar.quartz.*.mapper")
public class AvatarDQApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AvatarDQApplication.class);
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
