package com.wlgdo.avatar.admin.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Ligang.Wang[wlgchun@163.com]
 * @date: 2019/04/26
 */
public interface UserService {
	/**
	 * 用户列表
	 */
	JSONObject listUser(JSONObject jsonObject);

	/**
	 * 查询所有的角色
	 * 在添加/修改用户的时候要使用此方法
	 */
	JSONObject getAllRoles();

	/**
	 * 添加用户
	 */
	JSONObject addUser(JSONObject jsonObject);

	/**
	 * 修改用户
	 */
	JSONObject updateUser(JSONObject jsonObject);

	/**
	 * 角色列表
	 */
	JSONObject listRole();

	/**
	 * 查询所有权限, 给角色分配权限时调用
	 */
	JSONObject listAllPermission();

	/**
	 * 添加角色
	 */
	JSONObject addRole(JSONObject jsonObject);

	/**
	 * 修改角色
	 */
	JSONObject updateRole(JSONObject jsonObject);

	/**
	 * 删除角色
	 */
	JSONObject deleteRole(JSONObject jsonObject);
}
