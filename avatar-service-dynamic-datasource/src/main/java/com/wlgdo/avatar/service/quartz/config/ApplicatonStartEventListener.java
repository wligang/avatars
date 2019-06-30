package com.wlgdo.avatar.service.quartz.config;

import com.wlgdo.avatar.service.common.SpringUtil;
import com.wlgdo.avatar.service.quartz.entity.JobAndTrigger;
import com.wlgdo.avatar.service.quartz.service.IJobAndTriggerService;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.util.List;

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
