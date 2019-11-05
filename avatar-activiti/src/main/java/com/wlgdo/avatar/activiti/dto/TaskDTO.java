package com.wlgdo.avatar.activiti.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@ApiModel(value = "任务参数")
public class TaskDTO {
    @ApiModelProperty(value = "申请人 L11", hidden = true)
    private String applicant;
    @ApiModelProperty(value = "任务ID L11")
    private String tenantId;
    @ApiModelProperty(value = "受让人 L11")
    private String assignee;
    @ApiModelProperty(value = "委托人 L11")
    private String owner;
    @ApiModelProperty(value = "任务ID L11")
    private String taskId;
    @ApiModelProperty(value = "任务名称 L11")
    private String taskName;
    @ApiModelProperty(value = "任务标题 L11")
    private String title;
    @ApiModelProperty(value = "流程定义名称 L11", hidden = true)
    private String pdName;
    @ApiModelProperty(value = "流程版本 L11")
    private String version;
    @ApiModelProperty(value = "任务执行时间 L11")
    private LocalDateTime time;
    @ApiModelProperty(value = "流程实例ID L11")
    private String processInstanceId;
    @ApiModelProperty(value = "任务状态 L11")
    private String status;
    @ApiModelProperty(value = "任务节点key L11")
    private String nodeKey;
    @ApiModelProperty(value = "任务节点key L11")
    private List<String> nodeKeys;
    @ApiModelProperty(value = "任务流程key L11")
    private String processDefKey;
    @ApiModelProperty(value = "任务类别 L11")
    private String category;
    @ApiModelProperty(value = "当前多少页 L11")
    private Integer current;
    @ApiModelProperty(value = "每页大小 L11")
    private Integer size;

    @ApiModelProperty(value = "业务实体 L11")
    private Object bizObj;

}
