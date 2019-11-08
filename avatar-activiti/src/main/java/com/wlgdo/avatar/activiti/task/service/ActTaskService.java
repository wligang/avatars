package com.wlgdo.avatar.activiti.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.wlgdo.avatar.activiti.dto.ActBillDTO;
import com.wlgdo.avatar.activiti.dto.CommentDto;
import com.wlgdo.avatar.activiti.dto.TaskDTO;

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
