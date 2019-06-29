package com.wlgdo.avatar.service.quartz.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlgdo.avatar.service.quartz.entity.JobAndTrigger;

import java.util.List;

public interface JobAndTriggerMapper extends BaseMapper<JobAndTrigger> {

    IPage<JobAndTrigger> pageJobAndTriggerDetails(Page<JobAndTrigger> page);

    List<JobAndTrigger> pageJobAndTriggerDetails(Wrapper<JobAndTrigger> wrap);
}
