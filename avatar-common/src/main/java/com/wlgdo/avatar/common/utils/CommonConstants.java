/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package com.wlgdo.avatar.common.utils;

/**
 * @author lengleng
 * @date 2017/10/29
 */
public interface CommonConstants {
	/**
	 * header 中租户ID
	 */
	String TENANT_ID = "TENANT-ID";

	/**
	 * header 中版本信息
	 */
	String VERSION = "VERSION";

	/**
	 * 空租户
	 */
	Integer TENANT_ID_NULL = -1;

	/**
	 * 租户ID
	 */
	Integer TENANT_ID_1 = 1;

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";
	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 业务日志类型
	 */
	String LOG_TYPE_BUS = "0";
	/**
	 * 登录日志类型
	 */
	String LOG_TYPE_LOGIN = "1";
	/**
	 * 系统日志类型
	 */
	String LOG_TYPE_SYS = "2";

	/**
	 * 菜单
	 */
	String MENU = "0";

	/**
	 * 按钮
	 */
	String MENU_BUTTON = "1";

	/**
	 * 超级管理员角色ID
	 */
	Integer ROLE_ADMIN_ID = 1;

	/**
	 * 菜单树根节点
	 */
	Integer MENU_TREE_ROOT_ID = -1;

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "pigx-ui";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "pigx";

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

	/**
	 * 公共参数
	 */
	String PIG_PUBLIC_PARAM_KEY = "PIG_PUBLIC_PARAM_KEY";

	/**
	 * 手机验证码前缀
	 */
	String VALIDATE_PHONE_KEY = "VALIDATE_PHONE_KEY_";

	String VALIDATE_MAIL_KEY = "VALIDATE_MAIL_KEY_";
	/**
	 * 成功标记
	 */
	Integer SUCCESS = 0;
	/**
	 * 失败标记
	 */
	Integer FAIL = 1;

	/**
	 * 默认存储bucket
	 */
	String BUCKET_NAME = "safe";
	/**
	 * 模板文件
	 */
	String BUCKET_TEMPLATE_NAME = "template";

	/**
	 * 默认树根节点
	 */
	Integer NODE_ROOT_ID = 0;
}
