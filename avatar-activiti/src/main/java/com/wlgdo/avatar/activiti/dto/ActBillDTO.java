package com.wlgdo.avatar.activiti.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 隐患任务
 *
 * @author Ligang.Wang[@foxjk.com]
 * @date 2019-10-03 11:51:45
 */
@Data
public class ActBillDTO implements Serializable {
    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 任务提交时间
     */
    private Date time;

    /**
     * 批准信息
     */
    private String comment;

    /**
     * 连线信息
     */
    private List<String> flagList;

    /**
     * 任务标志
     */
    private String taskFlag;

    /**
     * 工作流类型，1：隐患风险流程，2：请假流程
     */
    private String categroy;

    /**
     * 对应的业务类主键
     */
    private String bizPk;

    private String actId;


}
