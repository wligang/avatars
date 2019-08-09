package com.wlgdo.avatar.admin;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author: Ligang.Wang[wlgchun@163.com]
 * @description: SpringBoot启动类
 * @date: 20190426
 */
@SpringBootApplication
@MapperScan("com.wlgdo.avatar.admin.dao")
public class AvatarAdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AvatarAdminApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(AvatarAdminApplication.class);
    }
}
