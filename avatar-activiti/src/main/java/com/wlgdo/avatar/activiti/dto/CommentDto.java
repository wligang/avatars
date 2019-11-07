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

import lombok.Data;

import java.util.Date;

/**
 * @author lengleng
 * @date 2018/9/28
 * 批注Dto
 */
@Data
public class CommentDto {
	/**
	 * unique identifier for this comment
	 */
	private String id;

	/**
	 * reference to the user that made the comment
	 */
	private String userId;

	/**
	 * time and date when the user made the comment
	 */
	private Date time;

	/**
	 * reference to the task on which this comment was made
	 */
	private String taskId;

	/**
	 * reference to the process instance on which this comment was made
	 */
	private String processInstanceId;

	/**
	 * reference to the type given to the comment
	 */
	private String type;

	/**
	 * the full comment message the user had related to the task and/or process instance
	 */
	private String fullMessage;
}
