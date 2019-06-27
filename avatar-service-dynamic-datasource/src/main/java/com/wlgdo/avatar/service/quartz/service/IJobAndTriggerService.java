package com.wlgdo.avatar.service.quartz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.service.quartz.entity.JobAndTrigger;

/**
 * Created by haoxy on 2018/9/28.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public interface IJobAndTriggerService extends IService<JobAndTrigger> {
    PageInfo<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);
}
