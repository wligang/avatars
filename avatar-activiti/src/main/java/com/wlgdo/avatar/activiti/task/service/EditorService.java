package com.wlgdo.avatar.activiti.task.service;

/**
 * @author lengleng
 * @date 2018/9/25
 */
public interface EditorService {
	/**
	 * 获取设计器页面的json
	 *
	 * @return
	 */
	Object getStencilset();

	/**
	 * 根据modelId获取model
	 *
	 * @param modelId
	 * @return
	 */
	Object getEditorJson(String modelId);

	/**
	 * 保存model信息
	 * @param modelId
	 * @param name
	 * @param description
	 * @param jsonXml
	 * @param svgXml
	 */
	void saveModel(String modelId, String name, String description, String jsonXml, String svgXml);
}
