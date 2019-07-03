package com.wlgdo.avatar.quartz.schedule.entity;


public enum  State {

    TRI_YEAR(1),

    TRI_MONTH(2),

    TRI_DAY(3),

    TRI_HOUR(4),

    TRI_SECOND(5),

    TRI_WEEK(6);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    State(Integer value) {
        this.value = value;
    }
}
