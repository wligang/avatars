package com.wlgdo.avatar.service.quartz.jobs;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class HelloJob implements BaseJob {

    private static Logger log = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {

    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getKey().getName();
        log.info("HelloJob Job执行时间jobName:{}{}, ", jobName, new Date());
    }
}

