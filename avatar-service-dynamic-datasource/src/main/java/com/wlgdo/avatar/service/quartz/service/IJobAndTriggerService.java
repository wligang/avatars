package com.wlgdo.avatar.service.quartz.service;


import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.service.quartz.entity.JobAndTrigger;

/**
 * Created by haoxy on 2018/9/28.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public interface IJobAndTriggerService {
    PageInfo<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);
}
