package com.wlgdo.avatar.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAdminServer
public class AvatarMonitorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvatarMonitorServerApplication.class, args);
    }

}
