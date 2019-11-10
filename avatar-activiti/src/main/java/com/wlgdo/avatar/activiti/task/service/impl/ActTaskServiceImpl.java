
package com.wlgdo.avatar.activiti.task.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.wlgdo.avatar.activiti.dto.ActBillDTO;
import com.wlgdo.avatar.activiti.dto.CommentDto;
import com.wlgdo.avatar.activiti.dto.TaskDTO;
import com.wlgdo.avatar.activiti.task.mapper.LeaveBillMapper;
import com.wlgdo.avatar.activiti.task.service.ActTaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author lengleng
 * @date 2018/9/25
 */
@Slf4j
@Service
@AllArgsConstructor
public class ActTaskServiceImpl implements ActTaskService {
	private static final String FLAG = "审批";
	private final LeaveBillMapper leaveBillMapper;
	private final TaskService taskService;
	private final RuntimeService runtimeService;
	private final RepositoryService repositoryService;
	private final HistoryService historyService;
	private final ProcessEngineFactoryBean processEngine;


	@Override
	public IPage getTaskByName(TaskDTO taskDTO, String name) {
		int page = taskDTO.getCurrent() == null ? 1 : taskDTO.getCurrent();
		int limit = taskDTO.getCurrent() == null ? 20 : taskDTO.getSize();
		TaskQuery taskQuery = taskService.createTaskQuery()
				.taskCandidateOrAssigned(name);
//				.taskTenantId(String.valueOf(TenantContextHolder.getTenantId()));
		if (StringUtils.isNotEmpty(taskDTO.getCategory())) {
			taskQuery.taskCategory(taskDTO.getCategory());
		}

		if (StringUtils.isNotEmpty(taskDTO.getNodeKey())) {
			taskQuery.taskDefinitionKeyLike("%" + taskDTO.getNodeKey());
//            if (taskDTO.getNodeKey().contains("zhi_pai")) {
//                taskQuery.or().taskDefinitionKeyLike("%shen_pi");
//            }
		}


		IPage result = new Page(page, limit).setTotal(taskQuery.count());
		RiskFormDTO riskformDto = new RiskFormDTO();
		List<TaskDTO> taskDTOList = taskQuery.orderByTaskCreateTime().desc()
				.listPage((page - 1) * limit, limit).stream().map(task -> {
					TaskDTO dto = new TaskDTO();
					dto.setTaskId(task.getId());
					dto.setTaskName(task.getName());
					dto.setProcessInstanceId(task.getProcessInstanceId());
					dto.setNodeKey(task.getTaskDefinitionKey());
					dto.setCategory(task.getCategory());

					Instant instant = task.getCreateTime().toInstant();
					ZoneId zoneId = ZoneId.systemDefault();
					dto.setTime(instant.atZone(zoneId).toLocalDateTime());
					riskformDto.setProcessInsId(task.getProcessInstanceId());
					R<RiskFormDTO> remoteRiskForm = remoteTaskFromService.getInfoByProcessInsId(riskformDto, SecurityConstants.FROM_IN);
					if (remoteRiskForm != null && remoteRiskForm.getCode() == 0) {
						dto.setBizObj(remoteRiskForm.getData());
					}
					return dto;
				}).collect(Collectors.toList());
		result.setRecords(taskDTOList);
		return result;
	}

	@Override
	public IPage getTaskHisByUserName(TaskDTO taskDTO, String username) {
		int page = taskDTO.getCurrent() == null ? 1 : taskDTO.getCurrent();
		int limit = taskDTO.getCurrent() == null ? 20 : taskDTO.getSize();
		HistoricTaskInstanceQuery finishedQuery = historyService.createHistoricTaskInstanceQuery()
				.taskCandidateUser(username).finished();
		IPage result = new Page(page, limit).setTotal(finishedQuery.count());
		RiskFormDTO riskformDto = new RiskFormDTO();
		List<TaskDTO> taskDTOList = finishedQuery.orderByTaskCreateTime().desc()
				.listPage((page - 1) * limit, limit).stream().map(task -> {
					TaskDTO dto = new TaskDTO();
					dto.setTaskId(task.getId());
					dto.setTaskName(task.getName());
					dto.setProcessInstanceId(task.getProcessInstanceId());
					dto.setNodeKey(task.getTaskDefinitionKey());
					dto.setCategory(task.getCategory());
					Instant instant = task.getCreateTime().toInstant();
					ZoneId zoneId = ZoneId.systemDefault();
					dto.setTime(instant.atZone(zoneId).toLocalDateTime());

					riskformDto.setProcessInsId(task.getProcessInstanceId());
					R<RiskFormDTO> remoteRiskForm = remoteTaskFromService.getInfoByProcessInsId(riskformDto, SecurityConstants.FROM_IN);
					if (remoteRiskForm != null && remoteRiskForm.getCode() == 0) {
						dto.setBizObj(remoteRiskForm.getData());
					}

					return dto;
				}).collect(Collectors.toList());
		result.setRecords(taskDTOList);
		return result;
	}

	/**
	 * 通过任务ID查询任务信息关联申请单信息
	 *
	 * @param taskId
	 * @return
	 */
	@Override
	public R<ActBillDTO> getTaskById(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId())
				.singleResult();

		String businessKey = pi.getBusinessKey();
		if (StrUtil.isNotBlank(businessKey)) {
			businessKey = businessKey.split("_")[1];
		}
		ActBillDTO.TaskOpsDTO taskOpsDTO = findOutFlagListByTaskId(task, pi);

		ActBillDTO actBillDto = new ActBillDTO();
		actBillDto.setProcessInsId(pi.getProcessInstanceId());
		actBillDto.setProcessDefKey(pi.getProcessDefinitionKey());
		actBillDto.setActId(pi.getActivityId());
		actBillDto.setCategroy(task.getCategory());
		actBillDto.setTaskId(taskId);
		actBillDto.setTime(task.getCreateTime());
		actBillDto.setFlagList(taskOpsDTO.comeLines);
		actBillDto.setBizPk(businessKey);
		actBillDto.setTaskOps(taskOpsDTO.ops);
		TaskTypeEnum byCategory = TaskTypeEnum.getByCategory(task.getCategory());
		switch (byCategory) {
			case RISK:
				R<RiskFormDTO> retRiskForm = remoteTaskFromService.getRiskFormInfo(businessKey, SecurityConstants.FROM_IN);
				if (retRiskForm.getCode() != 0) {
					return R.failed("该任务已被删除，无法进行该操作");
				}
				actBillDto.setState(retRiskForm.getData().getState());
				actBillDto.setRiskForm(retRiskForm.getData());
				return R.ok(actBillDto);
			case TICKET:
				R<TicketFormDTO> retTicketForm = remoteTaskFromService.getTaskFormInfo(byCategory, businessKey, SecurityConstants.FROM_IN);
				if (retTicketForm.getCode() != 0) {
					return R.failed(retTicketForm.getMsg());
				}
				actBillDto.setState(retTicketForm.getData().getStatus());
				actBillDto.setTicketForm(retTicketForm.getData());
				return R.ok(actBillDto);
			default:
				log.error("未定义的任务类型:{}", pi.getBusinessKey());
		}

		return R.failed("获取任务信息失败");

	}

	/**
	 * 提交任务
	 *
	 * @param actBillDTO 参数
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean submitTask(ActBillDTO actBillDTO) {
		log.info("开始处理流程:{}", actBillDTO);
		PigxUser user = SecurityUtils.getUser();
		String taskId = actBillDTO.getTaskId();
		String processInstanceId = actBillDTO.getProcessInsId();
		Authentication.setAuthenticatedUserId(SecurityUtils.getUser().getUsername());
		taskService.addComment(taskId, processInstanceId, actBillDTO.getComment());
		taskService.claim(taskId, user.getUsername());
		List<String> usernameList = Lists.newArrayList();
		Map<String, Object> variables = new HashMap<>(2);
		switch (TaskTypeEnum.getByCategory(actBillDTO.getCategroy())) {
			case RISK:
				if (CollUtil.isNotEmpty(actBillDTO.getDelegationUsers())) {
					usernameList = actBillDTO.getDelegationUsers();
					taskService.addComment(taskId, processInstanceId, "[委托原因]" + actBillDTO.getDelegationReason());
				} else {
					List<ActUser> actUsers = actUserService.list(Wrappers.<ActUser>lambdaQuery().eq(ActUser::getTenantId, user.getTenantId()).eq(ActUser::getCategory, actBillDTO.getCategroy()).eq(ActUser::getNode, actBillDTO.getProcessDefKey()));
					usernameList = actUsers.stream().map(ActUser::getUsername).collect(Collectors.toList());
				}
				if (usernameList.size() == 0) {
					usernameList.add(SecurityUtils.getUser().getUsername());
				}
				variables.put("candidateUsers", usernameList);
				if (StringUtils.isNotEmpty(actBillDTO.getState())) {
					variables.put("status", actBillDTO.getState());

				}

				taskService.complete(taskId, variables);
				ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
				if (pi != null) {
					remoteTaskFromService.updateRiskForm(RiskFormDTO.builder().id(Integer.valueOf(actBillDTO.getBizPk())).processInsId(processInstanceId).state(actBillDTO.getState()).build(), SecurityConstants.FROM_IN);
				}
				return Boolean.TRUE;
			case TICKET:
				variables = new HashMap<>(1);
				if (StringUtils.isNotEmpty(actBillDTO.getState())) {
					variables.put("status", actBillDTO.getState());
				}
				if ("yan_shou".equals(actBillDTO.getActId())) {
					actBillDTO.setState(TicketEnum.Status.COMPLETED.getStatus());
				}
				taskService.complete(taskId, variables);
				actBillDTO.setProcessInsId(processInstanceId);
				remoteTaskFromService.updateTicketForm(actBillDTO, SecurityConstants.FROM_IN);
				return Boolean.TRUE;
			default:
				log.error("错误的任务类型");
		}
		log.error("审批任务失败:{}", actBillDTO);
		return false;
	}

	@Override
	public Boolean submitTicketTask(ActBillDTO actBillDto) {
		Task task = taskService.createTaskQuery().processInstanceId(actBillDto.getProcessInsId()).list().get(0);
		Authentication.setAuthenticatedUserId(SecurityUtils.getUser().getUsername());
		task.setTenantId(TenantContextHolder.getTenantId().toString());
		taskService.addComment(task.getId(), actBillDto.getProcessInsId(), actBillDto.getComment());
		Map<String, Object> variables = Maps.newHashMap();
		variables.put("tenantId", TenantContextHolder.getTenantId().toString());
		taskService.complete(task.getId(), variables);
		remoteTaskFromService.updateTicketForm(actBillDto, SecurityConstants.FROM_IN);
		return true;
	}

	@Override
	public List<CommentDto> getCommentByTaskId(String taskId) {
		//使用当前的任务ID，查询当前流程对应的历史任务ID
		//使用当前任务ID，获取当前任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//获取流程实例ID
		List<CommentDto> commentDtoList = taskService
				.getProcessInstanceComments(task.getProcessInstanceId())
				.stream().map(comment -> {
							CommentDto commentDto = new CommentDto();
							commentDto.setId(comment.getId());
							commentDto.setTime(comment.getTime());
							commentDto.setType(comment.getType());
							commentDto.setTaskId(comment.getTaskId());
							commentDto.setUserId(comment.getUserId());
							commentDto.setFullMessage(comment.getFullMessage());
							commentDto.setProcessInstanceId(comment.getProcessInstanceId());
							return commentDto;
						}
				).collect(Collectors.toList());
		return commentDtoList;
	}


	@Override
	public List<CommentDto> getCommentHisByTaskId(String taskId) {
		List<Comment> taskComments = taskService.getTaskComments(taskId);
		log.info("该任务点的评论是:{}", JSONUtil.toJsonStr(taskComments));
		List<CommentDto> commentDtos = taskComments.stream().map(comment -> {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setTime(comment.getTime());
			commentDto.setType(comment.getType());
			commentDto.setTaskId(comment.getTaskId());
			commentDto.setUserId(comment.getUserId());
			commentDto.setFullMessage(comment.getFullMessage());
			commentDto.setProcessInstanceId(comment.getProcessInstanceId());
			return commentDto;
		}).collect(Collectors.toList());
		return commentDtos;
	}

	/**
	 * 查询process workflow
	 *
	 * @param proccessId
	 * @return
	 */
	@Override
	public R getTaskHisFlow(String proccessId) {
		List<HistoricActivityInstance> list1 = historyService.createHistoricActivityInstanceQuery()
				.activityType("userTask")
				.processInstanceId(proccessId).list();

		return R.ok(list1);
	}


	@Override
	public InputStream viewByProcessId(String processId) {
		return readView(processId);
	}

	/**
	 * 追踪图片节点
	 *
	 * @param taskId
	 */
	@Override
	public InputStream viewByTaskId(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		return readView(task.getProcessInstanceId());
	}

	private InputStream readView(String processInstanceId) {
		HistoricProcessInstance historicProcessInstance =
				historyService.createHistoricProcessInstanceQuery()
						.processInstanceId(processInstanceId).singleResult();
		String processDefinitionId = historicProcessInstance.getProcessDefinitionId();
		List<String> executedActivityIdList = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId)
				.orderByHistoricActivityInstanceId().asc().list()
				.stream().map(HistoricActivityInstance::getActivityId)
				.collect(Collectors.toList());

		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();

		return diagramGenerator.generateDiagram(
				bpmnModel, "png",
				executedActivityIdList, Collections.emptyList(),
				"宋体",
				"宋体",
				"宋体",
				null, 1.0);

	}

	public InputStream viewByTaskIdBak(String taskId) {
		//使用当前任务ID，获取当前任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		HistoricProcessInstance historicProcessInstance =
				historyService.createHistoricProcessInstanceQuery()
						.processInstanceId(processInstanceId).singleResult();
		String processDefinitionId = null;
		List<String> executedActivityIdList = new ArrayList<>();
		if (processInstance != null) {
			processDefinitionId = processInstance.getProcessDefinitionId();
			executedActivityIdList = this.runtimeService.getActiveActivityIds(processInstance.getId());
		} else if (historicProcessInstance != null) {
			processDefinitionId = historicProcessInstance.getProcessDefinitionId();
			executedActivityIdList = historyService.createHistoricActivityInstanceQuery()
					.processInstanceId(processInstanceId)
					.orderByHistoricActivityInstanceId().asc().list()
					.stream().map(HistoricActivityInstance::getActivityId)
					.collect(Collectors.toList());
		}

		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();

		return diagramGenerator.generateDiagram(
				bpmnModel, "png",
				executedActivityIdList, Collections.emptyList(),
				processEngine.getProcessEngineConfiguration().getActivityFontName(),
				processEngine.getProcessEngineConfiguration().getLabelFontName(),
				"宋体",
				null, 1.0);

	}

	private ActBillDTO.TaskOpsDTO findOutFlagListByTaskId(Task task, ProcessInstance pi) {
		//查询ProcessDefinitionEntiy对象
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(task.getProcessDefinitionId());

		ActivityImpl activityImpl = processDefinitionEntity.findActivity(pi.getActivityId());
		//获取当前活动完成之后连线的名称
		List<PvmTransition> outgoingTransitions = activityImpl.getOutgoingTransitions();
		List<String> nameList = activityImpl.getOutgoingTransitions()
				.stream().map(pvm -> {
					String name = (String) pvm.getProperty("name");
					return StrUtil.isNotBlank(name) ? name : FLAG;
				}).collect(Collectors.toList());
		Map<String, Object> variables = activityImpl.getVariables();


		ActBillDTO.TaskOpsDTO taskOpsDto = new ActBillDTO.TaskOpsDTO();
		taskOpsDto.setComeLines(nameList);
		taskOpsDto.setOps(Lists.newArrayList());
		outgoingTransitions.get(0).getDestination().getOutgoingTransitions().stream().forEach(p -> {
			if (p.getProperty(CONDITION_TXT) != null && p.getProperty(CONDITION_LABLE) != null) {
				log.info("下一步的网关：{}", p);
				taskOpsDto.ops.add(new ActBillDTO.Ops((String) p.getProperty(CONDITION_TXT), (String) p.getProperty(CONDITION_LABLE)));
			}
		});

		return taskOpsDto;
	}


}
