package com.wlgdo.avatar.quartz.schedule.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wlgdo.avatar.quartz.schedule.mapper.JobAndTriggerMapper;
import com.wlgdo.avatar.quartz.schedule.jobs.BaseJob;
import com.wlgdo.avatar.quartz.schedule.service.*;
import com.wlgdo.avatar.quartz.schedule.tool.DateUnit;
import com.wlgdo.avatar.quartz.schedule.entity.*;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.quartz.DateBuilder.futureDate;

@Service
public class JobAndTriggerServiceImpl extends ServiceImpl<JobAndTriggerMapper, JobAndTrigger> implements IJobAndTriggerService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IQrtzJobDetailsService iQrtzJobDetailsService;

    @Autowired
    private IQrtzCronTriggersService iQrtzCronTriggersService;

    @Autowired
    private IQrtzSchedulerStateService iQrtzSchedulerStateService;

    @Autowired
    private IQrtzTriggersService iQrtzTriggersService;

    @Autowired
    private IQrtzPausedTriggerGrpsService iQrtzPausedTriggerGrpsService;

    @Autowired
    private IQrtzLocksService iQrtzLocksService;

    @Autowired
    private IQrtzFiredTriggersService iQrtzFiredTriggersService;

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;


    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    //Simple Trigger
    @Transactional
    @Override
    public JobInfo addSimpleJob(JobInfo jobInfo) {
        // 启动调度器
        try {
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

            saveQuartzSechedulInfo(jobInfo);


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
    @Transactional
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

            saveQuartzSechedulInfo(jobInfo);

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
    public IPage<JobAndTrigger> pageJobAndTriggerDetails(IPage<JobAndTrigger> pageWrap, QueryWrapper<JobAndTrigger> wrapQuery) {
        IPage<QrtzTriggers> pageT = new Page<>(pageWrap.getCurrent(), pageWrap.getSize());
        IPage<QrtzTriggers> pageData = iQrtzTriggersService.page(pageT);
        logger.info("{}", pageData);

        IPage<JobAndTrigger> page = jobAndTriggerMapper.pageJobAndTriggerDetails((Page<JobAndTrigger>) pageWrap);

        return page;
    }

    @Override
    public List<JobAndTrigger> list() {
        JobAndTrigger jobAndTrigger = new JobAndTrigger();
        Wrapper<JobAndTrigger> wrap = new QueryWrapper<>(jobAndTrigger);
        List<JobAndTrigger> list = jobAndTriggerMapper.pageJobAndTriggerDetails(wrap);
        return list;
    }

    @Override
    @DS("#tenantName")
    public List<JobAndTrigger> list(String tenantName) {
        JobAndTrigger jobAndTrigger = new JobAndTrigger();
        Wrapper<JobAndTrigger> wrap = new QueryWrapper<>(jobAndTrigger);
        List<JobAndTrigger> list = jobAndTriggerMapper.pageJobAndTriggerDetails(wrap);
        return list;
    }

    @Transactional
    public void saveQuartzSechedulInfo(@NotNull JobInfo jobInfo) {
        String schedulName = jobInfo.getJobClassName();
        String schedulGroup = jobInfo.getJobGroupName();
        String triggerName = "triggerNmme";
        QrtzJobDetails jobDetailEntity = new QrtzJobDetails();
        jobDetailEntity.setJobGroup(schedulGroup);
        jobDetailEntity.setJobClassName(jobInfo.getJobClassName());
        jobDetailEntity.setJobName(jobInfo.getJobClassName());
        jobDetailEntity.setSchedName(schedulName);
        jobDetailEntity.setIsDurable("1");
        jobDetailEntity.setIsUpdateData("1");
        jobDetailEntity.setIsNonconcurrent("1");
        jobDetailEntity.setRequestsRecovery("");
        jobDetailEntity.setJobData("");
        iQrtzJobDetailsService.save(jobDetailEntity);


        QrtzTriggers triggersEntiy = new QrtzTriggers();
        triggersEntiy.setJobGroup(schedulGroup);
        triggersEntiy.setJobName(jobInfo.getJobClassName());
        triggersEntiy.setSchedName(schedulName);
        triggersEntiy.setTriggerState("0");
        triggersEntiy.setTriggerType("Cron");
        triggersEntiy.setTriggerName(triggerName);
        triggersEntiy.setTriggerGroup(schedulGroup);
        triggersEntiy.setStartTime(1000L);
        iQrtzTriggersService.save(triggersEntiy);

        QrtzCronTriggers cronEntity = new QrtzCronTriggers();
        cronEntity.setCronExpression(jobInfo.getCronExpression());
        cronEntity.setTriggerGroup(schedulGroup);
        cronEntity.setSchedName(schedulName);
        cronEntity.setTriggerName(triggerName);
        iQrtzCronTriggersService.save(cronEntity);

    }
}
