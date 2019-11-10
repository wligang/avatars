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

package com.wlgdo.avatar.activiti.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 任务
 *
 * @author Ligang.Wang[@foxjk.com]
 * @date 2019-10-03 11:51:45
 */
@ApiModel(value = "任务单审批参数")
@Data
public class ActBillDTO implements Serializable {
	/**
	 * 任务ID
	 */
	@NotEmpty(message = "任务ID不能为空")
	@ApiModelProperty(value = "任务ID L11", required = true)
	private String taskId;

	@ApiModelProperty(value = "用户ID L11")
	private Integer userId;

	@ApiModelProperty(value = "任务账号 L11")
	private String userName;
	/**
	 * 任务提交时间
	 */
	@ApiModelProperty(value = "任务提交时间 L0")
	private Date time;

	/**
	 * 批准信息
	 */
	@NotEmpty(message = "审批备注不能为空")
	@ApiModelProperty(value = "审批信息 L225", required = true)
	private String comment;

	/**
	 * 工作流类型
	 */
	@ApiModelProperty(value = "任务类型 L225")
	private String categroy;

	/**
	 * 对应的业务状态
	 */
	@ApiModelProperty(value = "任务类型 L225")
	private String state;
	/**
	 * 对应的业务ID
	 */
	@ApiModelProperty(value = "业务ID L11")
	private String bizPk;

	@ApiModelProperty(value = "被委托人(用户username)")
	private List<String> delegationUsers;

	@ApiModelProperty(value = "委托原因")
	private String delegationReason;

	/**
	 * 连线信息
	 */
	@ApiModelProperty(value = "流程连线信息 L225")
	private List<String> flagList;

	/**
	 * 任务标志
	 */
	@ApiModelProperty(value = "任务标 L225")
	private String taskFlag;

	@ApiModelProperty(value = "流程Id L11")
	private String actId;

	@NotEmpty(message = "流程Id不能为空")
	@ApiModelProperty(value = "流程Id L11")
	private String processInsId;

	@ApiModelProperty(value = "流程定义key L11")
	private String processDefKey;


	@ApiModelProperty(value = "任务节点信息 L0")
	private List<Ops> taskOps;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TaskOpsDTO implements Serializable {

		@ApiModelProperty(value = "流程连线信息")
		public List<String> comeLines;

		@ApiModelProperty(value = "流程操作信息")
		public List<Ops> ops;


	}

	@ApiModel(value = "流程操作信息")
	@Data
	public static class Ops {

		@ApiModelProperty(value = "流程连线信息")
		private String value;

		@ApiModelProperty(value = "流程连线信息")
		private String label;

		public Ops(String value, String label) {
			String regEx = "[^0-9]";
			Matcher m = Pattern.compile(regEx).matcher(value);
			this.value = m.replaceAll("").trim();
			this.label = label;
		}

	}
}
