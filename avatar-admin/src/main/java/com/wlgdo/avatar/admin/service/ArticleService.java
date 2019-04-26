package com.wlgdo.avatar.admin.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Ligang.Wang[wlgchun@163.com]
 * @date: 2019/04/26
 */
public interface ArticleService {
	/**
	 * 新增文章
	 */
	JSONObject addArticle(JSONObject jsonObject);

	/**
	 * 文章列表
	 */
	JSONObject listArticle(JSONObject jsonObject);

	/**
	 * 更新文章
	 */
	JSONObject updateArticle(JSONObject jsonObject);
}
