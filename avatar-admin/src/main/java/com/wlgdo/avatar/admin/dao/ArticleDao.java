package com.wlgdo.avatar.admin.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Ligang.Wang[wlgchun@163.com]
 * @date: 2019/04/26
 * @description: 文章Dao层
 */
public interface ArticleDao {
	/**
	 * 新增文章
	 */
	int addArticle(JSONObject jsonObject);

	/**
	 * 统计文章总数
	 */
	int countArticle(JSONObject jsonObject);

	/**
	 * 文章列表
	 */
	List<JSONObject> listArticle(JSONObject jsonObject);

	/**
	 * 更新文章
	 */
	int updateArticle(JSONObject jsonObject);
}
