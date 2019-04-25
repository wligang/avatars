package com.wlgdo.avatar.service;

import com.wlgdo.avatar.api.IUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wlgdo.avatar.service.dao")
public class AvatarServiceApplication implements CommandLineRunner {

    @Autowired
    private IUserService userService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(userService.getUserName());

        Thread.currentThread().join();
    }

    public static void main(String[] args) {
        SpringApplication.run(AvatarServiceApplication.class, args);
    }
}
