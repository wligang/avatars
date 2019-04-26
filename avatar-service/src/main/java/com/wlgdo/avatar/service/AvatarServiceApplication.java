package com.wlgdo.avatar.service;

import com.alibaba.dubbo.container.Main;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("com.wlgdo.avatar.service.dao")
public class AvatarServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AvatarServiceApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        Main.main(args);
    }

}
