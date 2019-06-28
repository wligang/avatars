package com.wlgdo.avatar.service.quartz.jobs;


import com.wlgdo.avatar.service.quartz.service.BaseJob;
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
        log.info("Hello Job执行时间: " + new Date());
    }
}

