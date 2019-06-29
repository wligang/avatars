package com.wlgdo.avatar.service.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlgdo.avatar.service.quartz.entity.JobAndTrigger;

public interface JobAndTriggerMapper extends BaseMapper<JobAndTrigger> {

    IPage<JobAndTrigger> getJobAndTriggerDetails(Page<JobAndTrigger> page);
}
