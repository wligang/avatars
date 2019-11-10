
package com.wlgdo.avatar.activiti.task.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {
	private static final String BPMN20_XML = ".bpmn20.xml";
	private final RepositoryService repositoryService;
	private final ObjectMapper objectMapper;
	private final EditorService editorService;

	/**
	 * 创建流程
	 *
	 * @param name
	 * @param key
	 * @param desc
	 * @param category
	 * @return
	 */
	@Override
	public Model create(String name, String key, String desc, String category) {
		try {
			if (!repositoryService.createModelQuery().modelKey(key).list().isEmpty()) {
				throw new FoxjkBizException("保存失败，已存在同类型的流程");
			}

			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode properties = objectMapper.createObjectNode();
			properties.put("process_author", SecurityConstants.PIGX_LICENSE);
			properties.put("process_id", key);
			properties.put("name", name);
			editorNode.set("properties", properties);
			ObjectNode stencilset = objectMapper.createObjectNode();
			stencilset.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.set("stencilset", stencilset);

			Model model = repositoryService.newModel();
			model.setKey(key);
			model.setName(name);
			model.setCategory(category);
			model.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(model.getKey()).count() + 1)));

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, model.getVersion());
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, desc);
			model.setMetaInfo(modelObjectNode.toString());
			if (model.getKey().startsWith("PT")) {
				model.setTenantId(String.valueOf(TenantContextHolder.getTenantId()));
			}

			repositoryService.saveModel(model);
			repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes("utf-8"));
			return model;
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException", e);
		}
		return null;
	}

	@Override
	public Model copy(String templateId, String name, String desc) {
		Integer tenantId = TenantContextHolder.getTenantId();
		Model templateIdModel = repositoryService.createModelQuery().modelId(templateId).list().get(0);
		List<Model> list = repositoryService.createModelQuery().modelTenantId(tenantId.toString()).modelCategory(templateIdModel.getCategory()).list();
		if (CollUtil.isNotEmpty(list)) {
			list.forEach(i -> {
				repositoryService.deleteModel(i.getId());
			});
		}

		String modelKey = String.format(PROCESS_PREFIX_KEY, TenantContextHolder.getTenantId(), templateIdModel.getCategory());
		Model ret = this.create(name, modelKey, desc, templateIdModel.getCategory());
		ObjectNode editorJson = (ObjectNode) editorService.getEditorJson(templateIdModel.getId());
		editorJson = (ObjectNode) editorJson.get("model");
		editorService.saveModel(ret.getId(), name, desc, editorJson.toString(), "");

		return templateIdModel;

	}

	/**
	 * 分页获取流程
	 *
	 * @param params
	 * @return
	 */
	@Override
	public IPage<Model> getModelPage(ProcessDTO params) {
		ModelQuery modelQuery = repositoryService.createModelQuery()
				.modelTenantId(String.valueOf(TenantContextHolder.getTenantId()))
				.orderByLastUpdateTime().desc();
		String category = params.getCategory();
		if (StrUtil.isNotBlank(category)) {
			modelQuery.modelCategory(category);
		}

		int page = params.getCurrent() == null ? 1 : params.getCurrent();
		int limit = params.getSize() == null ? 20 : params.getSize();

		IPage result = new Page(page, limit);
		result.setTotal(modelQuery.count());
		result.setRecords(modelQuery.listPage((page - 1) * limit, limit));
		return result;
	}

	@Override
	public IPage<Model> getTicketModelPage(ProcessDTO params) {
		ModelQuery modelQuery = repositoryService.createModelQuery()
				.modelCategory(TICKET_CATEGORY_KEY)
				.latestVersion().orderByLastUpdateTime().desc();
		int page = params.getCurrent() == null ? 1 : params.getCurrent();
		int limit = params.getSize() == null ? 20 : params.getSize();

		IPage result = new Page(page, limit);
		result.setTotal(modelQuery.count());
		result.setRecords(modelQuery.listPage((page - 1) * limit, limit));
		return result;
	}

	/**
	 * 删除流程
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Boolean removeModelById(String id) {
		repositoryService.deleteModel(id);
		return Boolean.TRUE;
	}

	/**
	 * 部署流程
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Boolean deploy(String id) {
		try {
			// 获取模型
			Model model = repositoryService.getModel(id);
			ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(model.getId()));
			BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(objectNode);

			String processName = model.getName();
			if (!StrUtil.endWithIgnoreCase(processName, BPMN20_XML)) {
				processName += BPMN20_XML;
			}
			Deployment deployment = null;
			if (model.getCategory().equals(TaskTypeEnum.TICKET.getCategory())) {
				deployment = repositoryService
						.createDeployment().name(model.getName())
						.category(model.getCategory())
						.addBpmnModel(processName, bpmnModel)
						.deploy();
			} else {
				// 部署流程
				deployment = repositoryService
						.createDeployment().name(model.getName())
						.category(model.getCategory())
						.addBpmnModel(processName, bpmnModel)
						.tenantId(String.valueOf(TenantContextHolder.getTenantId()))
						.deploy();
			}
			// 设置流程分类
			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
					.deploymentId(deployment.getId())
					.list();

			list.stream().forEach(processDefinition ->
					repositoryService.setProcessDefinitionCategory(processDefinition.getId(), model.getCategory()));
		} catch (Exception e) {
			log.error("部署失败，异常", e);
		}
		return Boolean.TRUE;
	}

	@Override
	public List<Model> getModelList(ProcessDTO params) {
		ModelQuery modelQuery = repositoryService.createModelQuery()
				.modelTenantId(String.valueOf(TenantContextHolder.getTenantId()))
				.latestVersion().orderByLastUpdateTime().desc();
		List<Model> modelList = modelQuery.list();

		modelList.addAll(repositoryService.createModelQuery()
				.modelCategory(TICKET_CATEGORY_KEY)
				.latestVersion().orderByLastUpdateTime().desc().list());
		return modelList;
	}


}
