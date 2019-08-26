package com.wlgdo.avatar.quartz.schedule.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlgdo.avatar.quartz.common.SpringUtil;
import com.wlgdo.avatar.quartz.schedule.entity.JobAndTrigger;
import com.wlgdo.avatar.quartz.schedule.entity.JobInfo;
import com.wlgdo.avatar.quartz.schedule.jobs.BaseJob;
import com.wlgdo.avatar.quartz.schedule.service.IJobAndTriggerService;
import com.wlgdo.avatar.quartz.common.DateUnit;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "job")
public class JobController {


    @Autowired
    private IJobAndTriggerService iJobAndTriggerService;

    @Autowired
    private DateUnit dateUnit;

    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    /**
     * 添加任务
     *
     * @param jobInfo
     * @throws Exception
     */
    @PostMapping(value = "/addjob")
    public Object addjob(@RequestBody JobInfo jobInfo) throws Exception {
        if ("".equals(jobInfo.getJobClassName()) || "".equals(jobInfo.getCronExpression())) {
            return null;
        }
        if (jobInfo.getTimeType() == null) {
            jobInfo = iJobAndTriggerService.addCronJob(jobInfo);
            return jobInfo;
        }
        jobInfo = iJobAndTriggerService.addSimpleJob(jobInfo);

        return jobInfo;
    }


    /**
     * 暂停任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value = "/pausejob")
    public void pausejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        jobPause(jobClassName, jobGroupName);
    }

    public void jobPause(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    /**
     * 恢复任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value = "/resumejob")
    public void resumejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        jobresume(jobClassName, jobGroupName);
    }

    public void jobresume(String jobClassName, String jobGroupName) throws Exception {

        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        scheduler.start();
    }

    /**
     * 更新任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping(value = "/reschedulejob")
    public void rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName,
                              @RequestParam(value = "jobGroupName") String jobGroupName,
                              @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        iJobAndTriggerService.jobRefreshSchedule(jobClassName, jobGroupName, cronExpression);
    }


    /**
     * 删除任务
     * 删除操作前应该暂停该任务的触发器，并且停止该任务的执行
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value = "/deletejob")
    public void deletejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        jobdelete(jobClassName, jobGroupName);
    }

    public void jobdelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    /**
     * 查询任务
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/queryjob")
    public Object queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        IPage<JobAndTrigger> page = new Page<>(pageNum, pageSize);
        ((Page<JobAndTrigger>) page).setOptimizeCountSql(true);
        QueryWrapper<JobAndTrigger> wrapQuery = new QueryWrapper<JobAndTrigger>();
        page = iJobAndTriggerService.pageJobAndTriggerDetails(page, wrapQuery);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("JobAndTrigger", page);
        map.put("number", page.getTotal());

        IJobAndTriggerService jobAndTriggerService = SpringUtil.getBean(IJobAndTriggerService.class);
        List<JobAndTrigger> list = jobAndTriggerService.list();
        for (JobAndTrigger jat : list) {
            if (CronExpression.isValidExpression(jat.getCronExpression())) {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jat.getCronExpression());
                CronTrigger trigger = TriggerBuilder.newTrigger().
                        withIdentity(jat.getJobClassName(), jat.getJobGroup())
                        .withSchedule(scheduleBuilder)
                        .build();
            }
        }
        return page;
    }

    /**
     * 根据类名称，通过反射得到该类，然后创建一个BaseJob的实例。
     * 由于NewJob和HelloJob都实现了BaseJob，
     * 所以这里不需要我们手动去判断。这里涉及到了一些java多态调用的机制
     *
     * @param classname
     * @return
     * @throws Exception
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }


}
