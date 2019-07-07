package com.wlgdo.avatar.quartz.schedule.config;


import com.wlgdo.avatar.quartz.schedule.entity.JobAndTrigger;
import com.wlgdo.avatar.quartz.schedule.jobs.BaseJob;
import com.wlgdo.avatar.quartz.schedule.service.IJobAndTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.List;
import java.util.Properties;


/**
 * Created by haoxy on 2018/9/28.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Slf4j
@Configuration
public class SchedulerConfig {

    @Autowired
    private IJobAndTriggerService iJobAndTriggerService;

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        factory.setStartupDelay(10);
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * quartz初始化监听器
     * 这个监听器可以监听到工程的启动，在工程停止再启动时可以让已有的定时任务继续进行。
     *
     * @return
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        try {

            List<JobAndTrigger> list = iJobAndTriggerService.list();
            for (JobAndTrigger jat : list) {
                if (!CronExpression.isValidExpression(jat.getCronExpression())) {
                    continue;
                }
                BaseJob job = (BaseJob) Class.forName(jat.getJobClassName()).newInstance();
                JobDetail jobDetail = JobBuilder.newJob(job.getClass()).
                        withIdentity(jat.getJobClassName(), jat.getTriggerGroup())
                        .build();

                //表达式调度构建器(即任务执行的时间)
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jat.getCronExpression());
                //按新的cronExpression表达式构建一个新的trigger
                CronTrigger trigger = TriggerBuilder.newTrigger().
                        withIdentity(jat.getJobClassName(), jat.getTriggerGroup())
                        .withSchedule(scheduleBuilder)
                        .build();
                scheduler().scheduleJob(jobDetail, trigger);
            }
            log.info("=========avatar.wlgdo|quartz jobs begined======");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new QuartzInitializerListener();
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */

    @Bean(name = "Scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}
