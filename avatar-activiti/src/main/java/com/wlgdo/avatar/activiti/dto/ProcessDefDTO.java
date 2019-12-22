package com.wlgdo.avatar.activiti.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

@Data
@ApiModel(value = "流程定义模型")
public class ProcessDefDTO {
    @ApiModelProperty(value = "流程类别 L11")
    private String category;
    @ApiModelProperty(value = "流程定义ID L11")
    private String processonDefinitionId;
    @ApiModelProperty(value = "流程类型，一个公司一个类型只能一个 L11")
    private String key;
    @ApiModelProperty(value = "流程名称 L11")
    private String name;
    private Integer revision;
    @ApiModelProperty(value = "流程部署时间，时间戳类型 L0")
    private Long deploymentTime;
    private String xmlName;
    private String picName;
    private String deploymentId;
    private Boolean suspend;
    private String description;
    private Integer xAxis;
    private Integer yAxis;
    private Integer width;
    private Integer height;


    /**
     * 抽取流程定义需要返回的内容
     *
     * @param processDefinition
     * @param deployment
     * @return
     */
    public static ProcessDefDTO toProcessDefDTO(ProcessDefinition processDefinition, Deployment deployment) {
        ProcessDefDTO dto = new ProcessDefDTO();
        dto.setCategory(processDefinition.getCategory());
        dto.setProcessonDefinitionId(processDefinition.getId());
        dto.setKey(processDefinition.getKey());
        dto.setName(deployment.getName());
        dto.setRevision(processDefinition.getVersion());
        dto.setDeploymentTime(deployment.getDeploymentTime().getTime());
        dto.setXmlName(processDefinition.getResourceName());
        dto.setPicName(processDefinition.getDiagramResourceName());
        dto.setDeploymentId(deployment.getId());
        dto.setSuspend(processDefinition.isSuspended());
        dto.setDescription(processDefinition.getDescription());
        return dto;
    }
}
