package com.wlgdo.avatar.service.quartz.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wlgdo.avatar.service.quartz.mapper.JobAndTriggerMapper;
import com.wlgdo.avatar.service.quartz.entity.JobAndTrigger;
import com.wlgdo.avatar.service.quartz.service.IJobAndTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class IJobAndTriggerServiceImpl extends ServiceImpl<JobAndTriggerMapper, JobAndTrigger>implements IJobAndTriggerService {

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;


    @Override
    public IPage<JobAndTrigger>  getJobAndTriggerDetails(Integer pageNum, Integer pageSize) {

        IPage<JobAndTrigger> page = jobAndTriggerMapper.getJobAndTriggerDetails();
        if (page==null){
            return new Page<>();
        }
        return page;
    }
}
