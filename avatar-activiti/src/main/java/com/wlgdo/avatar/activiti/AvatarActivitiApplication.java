package com.wlgdo.avatar.activiti;


import com.wlgdo.avatar.activiti.listenner.ApplicatonCloseEventListener;
import com.wlgdo.avatar.activiti.listenner.ApplicatonStartEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvatarActivitiApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AvatarActivitiApplication.class);

        application.addListeners(new ApplicatonStartEventListener());
        application.addListeners(new ApplicatonCloseEventListener());
        application.run(args);
    }

}
