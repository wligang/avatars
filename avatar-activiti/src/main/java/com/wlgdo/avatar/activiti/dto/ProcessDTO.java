package com.wlgdo.avatar.activiti.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Ligang.Wang[@foxjk.com]
 * @date 2019-09-19 14:28:42
 */
@Data
@ApiModel(value = "流程参数")
public class ProcessDTO {
    @ApiModelProperty(value = "排序字段 L11")
    private String descs;
    @ApiModelProperty(value = "当前页 L11")
    private Integer current;
    @ApiModelProperty(value = "每页大小 L11")
    private Integer size;
    @ApiModelProperty(value = "流程类别 L11")
    private String category;
    @ApiModelProperty(value = "公司ID L11")
    private String tenantId;


}
