package com.wlgdo.avatar.service.quartz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wlgdo.avatar.service.quartz.entity.JobAndTrigger;
import com.wlgdo.avatar.service.quartz.entity.JobInfo;


public interface IJobAndTriggerService extends IService<JobAndTrigger> {

    IPage<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);

    JobInfo addSimpleJob(JobInfo jobInfo);

    JobInfo addCronJob(JobInfo jobInfo);

    void jobRefreshSchedule(String jobClassName, String jobGroupName, String cronExpression);
}
