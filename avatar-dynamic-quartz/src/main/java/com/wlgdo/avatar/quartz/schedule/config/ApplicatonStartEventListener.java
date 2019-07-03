package com.wlgdo.avatar.quartz.schedule.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date : 2019/3/21
 */
public class ApplicatonStartEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("Spring Application started........ ");
    }

}
