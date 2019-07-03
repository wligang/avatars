package com.wlgdo.avatar.quartz.schedule.jobs;

import com.wlgdo.avatar.quartz.common.SpringUtil;
import com.wlgdo.avatar.quartz.schedule.entity.JobAndTrigger;
import com.wlgdo.avatar.quartz.schedule.service.IJobAndTriggerService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;


public class NewJob implements BaseJob {

    private static Logger log = LoggerFactory.getLogger(NewJob.class);

    public NewJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getKey().getName();
        String group = context.getJobDetail().getKey().getGroup();
        IJobAndTriggerService iJobAndTriggerService = SpringUtil.getBean(IJobAndTriggerService.class);
        List<JobAndTrigger> lists = iJobAndTriggerService.list(group);
        log.info("获取到的NewJob列表：{}", lists);
        log.info("NewJob Job执行时间jobName:{}{}, ", jobName, new Date());
    }
}
