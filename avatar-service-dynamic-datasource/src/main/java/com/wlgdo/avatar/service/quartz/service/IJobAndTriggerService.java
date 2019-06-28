package com.wlgdo.avatar.service.quartz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wlgdo.avatar.service.quartz.entity.JobAndTrigger;


public interface IJobAndTriggerService extends IService<JobAndTrigger> {

    IPage<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);
}
