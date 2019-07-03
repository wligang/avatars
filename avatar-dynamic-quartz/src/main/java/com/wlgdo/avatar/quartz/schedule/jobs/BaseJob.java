package com.wlgdo.avatar.quartz.schedule.jobs;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public interface BaseJob extends Job {

    public void execute(JobExecutionContext context) throws JobExecutionException;
}
