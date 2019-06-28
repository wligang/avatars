package com.wlgdo.avatar.service.quartz.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wlgdo.avatar.service.quartz.entity.JobInfo;
import com.wlgdo.avatar.service.quartz.mapper.JobAndTriggerMapper;
import com.wlgdo.avatar.service.quartz.entity.JobAndTrigger;
import com.wlgdo.avatar.service.quartz.service.BaseJob;
import com.wlgdo.avatar.service.quartz.service.IJobAndTriggerService;
import com.wlgdo.avatar.service.quartz.tool.DateUnit;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static org.quartz.DateBuilder.futureDate;

@Service
public class IJobAndTriggerServiceImpl extends ServiceImpl<JobAndTriggerMapper, JobAndTrigger> implements IJobAndTriggerService {

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;

    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    //Simple Trigger
    @Override
    public JobInfo addSimpleJob(JobInfo jobInfo) {
        // 启动调度器
        try {
            scheduler.start();

            //构建job信息
            BaseJob job = (BaseJob) Class.forName(jobInfo.getJobClassName()).newInstance();

            JobDetail jobDetail = JobBuilder.newJob(job.getClass())
                    .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .build();

            DateBuilder.IntervalUnit verDate = DateUnit.verification(jobInfo.getTimeType());
            SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                    .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .startAt(futureDate(Integer.parseInt(jobInfo.getCronExpression()), verDate))
                    .forJob(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .build();

            scheduler.scheduleJob(jobDetail, simpleTrigger);

        } catch (SchedulerException e) {
            log.error("创建定时任务失败:{}", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return jobInfo;
    }


    //CronTrigger
    @Override
    public JobInfo addCronJob(JobInfo jobInfo) {
        try {
            // 启动调度器
            //构建job信息
            BaseJob job = (BaseJob) Class.forName(jobInfo.getJobClassName()).newInstance();
            JobDetail jobDetail = JobBuilder.newJob(job.getClass()).
                    withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .build();

            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression());
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().
                    withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .withSchedule(scheduleBuilder)
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SchedulerException e) {
            log.error("创建定时任务失败:{}", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return jobInfo;
    }

    @Override
    public void jobRefreshSchedule(String jobClassName, String jobGroupName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("创建定时任务失败:{}", e);
        }
    }


    @Override
    public IPage<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize) {
        IPage<JobAndTrigger> page = jobAndTriggerMapper.getJobAndTriggerDetails();
        if (page == null) {
            return new Page<>();
        }
        return page;
    }
}
