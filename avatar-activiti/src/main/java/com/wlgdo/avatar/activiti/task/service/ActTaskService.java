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

package com.wlgdo.avatar.activiti.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.InputStream;
import java.util.List;


public interface ActTaskService {

    public static final String CONDITION_TXT = "conditionText";
    public static final String CONDITION_LABLE = "name";

    /**
     * 获取用户代办列表
     *
     * @param taskDTO
     * @param name
     * @return
     */
    IPage getTaskByName(TaskDTO taskDTO, String name);

    IPage getTaskHisByUserName(TaskDTO taskDTO, String name);

    /**
     * 通过任务ID查询任务信息关联申请单信息
     *
     * @param id
     * @return
     */
    R<ActBillDTO> getTaskById(String id);

    /**
     * 提交任务
     *
     * @param activitiBill
     * @return
     */
    Boolean submitTask(ActBillDTO activitiBill);


    Boolean submitTicketTask(ActBillDTO actBillDto);

    /**
     * 通过任务ID 查询批注信息
     *
     * @param taskId 任务ID
     * @return
     */
    List<CommentDto> getCommentByTaskId(String taskId);

    /**
     * 追踪图片节点
     *
     * @param id
     * @return
     */
    InputStream viewByTaskId(String id);

    List<CommentDto> getCommentHisByTaskId(String taskId);

    R getTaskHisFlow(String proccessId);

    InputStream viewByProcessId(String processId);

}
