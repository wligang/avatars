
package com.wlgdo.avatar.activiti.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Ligang.Wang
 * @date 2019/10/06
 */
@Data
@ApiModel("流程模型参数")
public class ModelForm {

    @NotBlank
    private String category;

    @NotBlank
    private String name;

    @NotBlank
    private String desc;

    private String key;

    private String type;

    private String templateId;


}
