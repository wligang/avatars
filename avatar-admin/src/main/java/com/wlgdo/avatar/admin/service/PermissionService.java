package com.wlgdo.avatar.admin.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Ligang.Wang[wlgchun@163.com]
 * @date: 2019/04/26
 */
public interface PermissionService {
	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	JSONObject getUserPermission(String username);
}
