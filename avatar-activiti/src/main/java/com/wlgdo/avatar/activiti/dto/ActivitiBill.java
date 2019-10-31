/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pig4cloud.pigx.act.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.pig4cloud.pigx.admin.api.dto.ahj.RiskFormDTO;
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
public class ActivitiBill implements Serializable {
    /**
     * 任务ID
     */
    @TableField(exist = false)
    private String taskId;

    /**
     * 任务提交时间
     */
    @TableField(exist = false)
    private Date time;

    /**
     * 批准信息
     */
    @TableField(exist = false)
    private String comment;

    /**
     * 连线信息
     */
    @TableField(exist = false)
    private List<String> flagList;

    /**
     * 任务标志
     */
    @TableField(exist = false)
    private String taskFlag;

    /**
     * 工作流类型，1：隐患风险流程，2：请假流程
     */
    @TableField(exist = false)
    private String categroy;

    /**
     * 对应的业务类主键
     */
    @TableField(exist = false)
    private String bizPk;

//    @TableField(exist = false)
//    private List<ActivitiOpsDTO.Ops> ops;

    @TableField(exist = false)
    private String actId;


}
