package com.wlgdo.avatar.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: admin Application
 * @author: Ligang.Wang[wangligang@eglagame.com]
 * @create: 2019-08-08 20:49
 **/

@SpringBootApplication
public class AvatarAdminApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AvatarAdminApplication.class);
        application.run(args);


    }

}
