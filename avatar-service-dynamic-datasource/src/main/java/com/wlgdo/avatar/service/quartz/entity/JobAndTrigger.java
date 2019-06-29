package com.wlgdo.avatar.service.quartz.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JobAndTrigger {

    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String triggerGroup;
    private BigInteger repeatInterval;
    private BigInteger timesTriggered;
    private String cronExpression;
    private String timeZone_id;
}
