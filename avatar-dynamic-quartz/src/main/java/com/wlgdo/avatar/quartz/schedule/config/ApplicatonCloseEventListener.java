package com.wlgdo.avatar.quartz.schedule.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date : 2019/3/21
 */


public class ApplicatonCloseEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("spring IOC is closed");
    }
}
