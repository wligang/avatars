
package com.wlgdo.avatar.activiti.task.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlgdo.avatar.activiti.constance.ProcessStatusEnum;
import com.wlgdo.avatar.activiti.constance.ResourceTypeEnum;
import com.wlgdo.avatar.activiti.dto.ActBillDTO;
import com.wlgdo.avatar.activiti.dto.ProcessDTO;
import com.wlgdo.avatar.activiti.dto.ProcessDefDTO;
import com.wlgdo.avatar.activiti.task.entity.LeaveBill;
import com.wlgdo.avatar.activiti.task.mapper.LeaveBillMapper;
import com.wlgdo.avatar.activiti.task.service.ActTaskService;
import com.wlgdo.avatar.activiti.task.service.ModelService;
import com.wlgdo.avatar.activiti.task.service.ProcessService;
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

    /**
     * 分页流程列表
     *
     * @param params
     * @return
     */
    @Override
    public IPage<ProcessDefDTO> getProcessByPage(ProcessDTO params) {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery()
                .processDefinitionTenantId(String.valueOf(0)).latestVersion();

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
        leaveBill.setState("LeaveBill");

        String key = leaveBill.getClass().getSimpleName();
        String businessKey = key + "_" + leaveBill.getLeaveId();
        runtimeService.startProcessInstanceByKeyAndTenantId(key, businessKey, String.valueOf(0));
        leaveBillMapper.updateById(leaveBill);
        return Boolean.TRUE;
    }



}
