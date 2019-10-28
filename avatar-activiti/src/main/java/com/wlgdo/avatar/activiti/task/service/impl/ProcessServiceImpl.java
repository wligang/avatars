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

package com.wlgdo.avatar.activiti.task.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Ligang.Wang[@wlgdo.com]
 */
@Service
@AllArgsConstructor
public class ProcessServiceImpl implements ProcessService {
    private final RepositoryService repositoryService;
    private final RuntimeService runtimeService;
    private final LeaveBillMapper leaveBillMapper;
    private final CheckBillService checkBillService;
    private final ActTaskService actTaskService;
    private final TaskService taskService;

    /**
     * 分页流程列表
     *
     * @param params
     * @return
     */
    @Override
    public IPage<ProcessDefDTO> getProcessByPage(ProcessDTO params) {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery()
                .processDefinitionTenantId(String.valueOf(TenantContextHolder.getTenantId())).latestVersion();

        String category = params.getCategory();
        if (StrUtil.isNotBlank(category)) {
            query.processDefinitionCategory(category);
        }

        int page = params.getCurrent() == null ? 1 : params.getCurrent();
        int limit = params.getCurrent() == null ? 20 : params.getSize();

        IPage result = new Page(page, limit);
        result.setTotal(query.count());

        List<ProcessDefDTO> deploymentList = query.listPage((page - 1) * limit, limit)
                .stream()
                .map(processDefinition -> {
                    Deployment deployment = repositoryService.createDeploymentQuery()
                            .deploymentId(processDefinition.getDeploymentId()).singleResult();
                    return ProcessDefDTO.toProcessDefDTO(processDefinition, deployment);
                }).collect(Collectors.toList());
        result.setRecords(deploymentList);
        return result;
    }

    /**
     * 读取xml/image资源
     *
     * @param procDefId
     * @param proInsId
     * @param resType
     * @return
     */
    @Override
    public InputStream readResource(String procDefId, String proInsId, String resType) {

        if (StrUtil.isBlank(procDefId)) {
            ProcessInstance processInstance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(proInsId)
                    .singleResult();
            procDefId = processInstance.getProcessDefinitionId();
        }
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionId(procDefId)
                .singleResult();

        String resourceName = "";
        if (ResourceTypeEnum.IMAGE.getType().equals(resType)) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (ResourceTypeEnum.XML.getType().equals(resType)) {
            resourceName = processDefinition.getResourceName();
        }

        InputStream resourceAsStream = repositoryService
                .getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
    }

    /**
     * 更新状态
     *
     * @param status
     * @param procDefId
     * @return
     */
    @Override
    public Boolean updateStatus(String status, String procDefId) {
        if (ProcessStatusEnum.ACTIVE.getStatus().equals(status)) {
            repositoryService.activateProcessDefinitionById(procDefId, true, null);
        } else if (ProcessStatusEnum.SUSPEND.getStatus().equals(status)) {
            repositoryService.suspendProcessDefinitionById(procDefId, true, null);
        }
        return Boolean.TRUE;
    }

    /**
     * 删除部署的流程，级联删除流程实例
     *
     * @param deploymentId
     * @return
     */
    @Override
    public Boolean removeProcIns(String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
        return Boolean.TRUE;
    }

    /**
     * 启动流程、更新请假单状态
     *
     * @param leaveId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveStartProcess(Integer leaveId) {
        LeaveBill leaveBill = leaveBillMapper.selectById(leaveId);
        leaveBill.setState(TaskStatusEnum.CHECK.getStatus());

        String key = leaveBill.getClass().getSimpleName();
        String businessKey = key + "_" + leaveBill.getLeaveId();
        runtimeService.startProcessInstanceByKeyAndTenantId(key, businessKey, String.valueOf(TenantContextHolder.getTenantId()));
        leaveBillMapper.updateById(leaveBill);
        return Boolean.TRUE;
    }

    @Override
    public Boolean saveStartRiskCheckProcess(Integer taskId) {
        CheckBill checkBill = checkBillService.getById(taskId);
        checkBill.setState(RiskTaskStatusEnum.UNAUDIT.getStatus());

        String key = checkBill.getClass().getSimpleName();
        String businessKey = key + "_" + checkBill.getCheckId();
        runtimeService.startProcessInstanceByKeyAndTenantId(key, businessKey, String.valueOf(TenantContextHolder.getTenantId()));
        checkBillService.updateById(checkBill);
        return Boolean.TRUE;
    }

    @Override
    public String startRiskAuditProcess(Integer riskId) {
        Integer tenantId = TenantContextHolder.getTenantId();
        String modelKey = String.format(ModelService.PROCESS_PREFIX_KEY, tenantId, TaskTypeEnum.RISK.getCategory());
        String businessKey = modelKey + "_" + riskId;
        ProcessInstance ret = runtimeService.startProcessInstanceByKeyAndTenantId(modelKey, businessKey, String.valueOf(tenantId));
        Task task = taskService.createTaskQuery().processInstanceId(ret.getId()).list().get(0);
        //this process should auto commit
        ActBillDTO actBillDto = new ActBillDTO();
        actBillDto.setBizPk(String.valueOf(riskId));
        actBillDto.setComment("提交隐患");
        actBillDto.setCategroy(TaskTypeEnum.RISK.getCategory());
        actBillDto.setState(TaskStatusEnum.CHECK.getStatus());
        actBillDto.setTaskId(task.getId());
        actBillDto.setProcessInsId(ret.getProcessInstanceId());
        actTaskService.submitTask(actBillDto);

        return ret.getId();
    }

    @Override
    public ActBillDTO startJobTicketActiviti(Integer userid, Integer bizPk, TicketEnum.Type type) {
        String modelKey = String.format(ModelService.TICKET_PREFIX_KEY, type);
        String businessKey = modelKey + "_" + bizPk;
        ProcessInstance ret = runtimeService.startProcessInstanceByKey(modelKey, businessKey);

        ActBillDTO actBillDto = new ActBillDTO();
        actBillDto.setUserId(userid);
        actBillDto.setBizPk(String.valueOf(bizPk));
        actBillDto.setComment(String.format("提交%s", type.getDesc()));
        actBillDto.setCategroy(TaskTypeEnum.TICKET.getCategory());
        actBillDto.setState(TicketEnum.Status.CHECKING.getStatus());
        actBillDto.setProcessInsId(ret.getProcessInstanceId());
        actTaskService.submitTicketTask(actBillDto);
        return actBillDto;
    }

}
