/*
 *    Copyright (c) 2019-2025, @Foxjk.com All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the www.foxjk.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: Ligang.Wang (@foxJk.com)
 */

package com.wlgdo.avatar.activiti.task.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.act.process.entity.CheckBill;
import com.pig4cloud.pigx.act.process.service.CheckBillService;
import com.pig4cloud.pigx.act.process.service.ProcessService;
import com.pig4cloud.pigx.common.core.constant.enums.RiskTaskStatusEnum;
import com.pig4cloud.pigx.common.core.constant.enums.foxjk.DelFlagEnum;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 风险管理流程
 *
 * @author Ligang.Wang[@foxjk.com]
 * @date 2019-10-03 11:51:45
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/risk-check")
@Api(value = "risk-check", tags = "风险管理流程管理" ,hidden = true)
public class CheckBillController {

    private final CheckBillService checkBillService;
    private final ProcessService processService;

    /**
     * 分页查询
     *
     * @param page      分页对象
     * @param checkBill 风险管理流程
     * @return
     */
    @ApiOperation(value = "分页查询风险管理流程", notes = "分页查询风险管理流程")
    @GetMapping("/page")
    public R<IPage<CheckBill>> getCheckBillPage(Page page, CheckBill checkBill) {
        String username = SecurityUtils.getUser().getUsername();

        return R.ok(checkBillService.page(page, Wrappers.<CheckBill>query(checkBill).eq("username", username).orderByDesc("update_time", "state")));
    }


    /**
     * 通过id查询风险管理流程
     *
     * @param taksId id
     * @return R
     */
    @ApiOperation(value = "通过id查询风险管理流程", notes = "通过id查询风险管理流程")
    @GetMapping("/{taksId}")
    public R<CheckBill> getById(@PathVariable("taksId") Integer taksId) {
        return R.ok(checkBillService.getById(taksId));
    }

    /**
     * 新增风险管理流程
     *
     * @param checkBill 风险管理流程
     * @return R
     */
    @ApiOperation(value = "新增风险管理流程", notes = "新增风险管理流程")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('risk_checkbill_add')")
    public R save(@RequestBody CheckBill checkBill) {
        checkBill.setUsername(SecurityUtils.getUser().getUsername());
        checkBill.setState(RiskTaskStatusEnum.UNSUBMIT.getStatus());
        checkBill.setDelFlag(DelFlagEnum.NORMAL.getValue());
        return R.ok(checkBillService.save(checkBill));
    }

    /**
     * 修改风险管理流程
     *
     * @param checkBill 风险管理流程
     * @return R
     */
    @ApiOperation(value = "修改风险管理流程", notes = "修改风险管理流程")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('risk_checkbill_edit')")
    public R updateById(@RequestBody CheckBill checkBill) {
        return R.ok(checkBillService.updateById(checkBill));
    }

    /**
     * 通过id删除风险管理流程
     *
     * @param taksId id
     * @return R
     */
    @ApiOperation(value = "通过id删除风险管理流程", notes = "通过id删除风险管理流程")
    @DeleteMapping("/{taksId}")
    @PreAuthorize("@pms.hasPermission('risk_checkbill_del')")
    public R removeById(@PathVariable Integer taksId) {
        return R.ok(checkBillService.removeById(taksId));
    }


    /**
     * 提交风险流程
     *
     * @param taskId 任务ID
     * @return R
     */
    @GetMapping("/submit/{taskId}")
    public R submit(@PathVariable("taskId") Integer taskId) {
        return R.ok(processService.saveStartRiskCheckProcess(taskId));
    }
}
