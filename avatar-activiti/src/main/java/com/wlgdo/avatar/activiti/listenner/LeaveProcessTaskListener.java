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

package com.wlgdo.avatar.activiti.listenner;

import cn.hutool.core.collection.CollUtil;
import com.pig4cloud.pigx.admin.api.entity.SysUser;
import com.pig4cloud.pigx.admin.api.feign.RemoteUserService;
import com.pig4cloud.pigx.common.core.constant.enums.TaskTypeEnum;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.core.util.SpringContextHolder;
import com.pig4cloud.pigx.common.data.tenant.TenantContextHolder;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lengleng
 * @date 2018/9/27
 * 请假流程监听器
 */
@Slf4j
public class LeaveProcessTaskListener implements TaskListener {

    /**
     * 查询提交人的上级
     *
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setCategory(TaskTypeEnum.LEAVE.getCategory());
        RemoteUserService userService = SpringContextHolder.getBean(RemoteUserService.class);
        R<List<SysUser>> result = userService.ancestorUsers(SecurityUtils.getUser().getUsername());
        List<String> remindUserList = new ArrayList<>();

        if (CollUtil.isEmpty(result.getData())) {
            log.info("用户 {} 不存在上级,任务单由当前用户审批", SecurityUtils.getUser().getUsername());
            delegateTask.addCandidateUser(SecurityUtils.getUser().getUsername());
            remindUserList.add(SecurityUtils.getUser().getUsername());
        } else {
            List<String> userList = result.getData().stream().map(SysUser::getUsername).collect(Collectors.toList());
            log.info("当前任务 {}，由 {}处理", delegateTask.getId(), userList);
            delegateTask.addCandidateUsers(userList);
            delegateTask.setCategory("LeaveBill");
            remindUserList.addAll(userList);
        }
        SimpMessagingTemplate simpMessagingTemplate = SpringContextHolder.getBean(SimpMessagingTemplate.class);
        remindUserList.forEach(user -> {
            String target = String.format("%s-%s", SecurityUtils.getUser().getUsername(), TenantContextHolder.getTenantId());
            simpMessagingTemplate.convertAndSendToUser(target, "/remind", delegateTask.getName());
        });
    }
}
