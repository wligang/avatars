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

package com.wlgdo.avatar.activiti.task.controller;

import cn.hutool.core.io.IoUtil;
import com.pig4cloud.pigx.act.process.dto.ProcessDTO;
import com.pig4cloud.pigx.act.process.service.ProcessService;
import com.pig4cloud.pigx.common.core.constant.enums.ResourceTypeEnum;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

/**
 * @author lengleng
 * @date 2018/9/25
 */
@Api(value = "流程管理接口", tags = "流程管理接口")
@RestController
@AllArgsConstructor
@RequestMapping("/process")
public class ProcessController {

    private final ProcessService processService;

    @ApiOperation(value = "分页查询隐患流程", notes = "分页查询隐患流程")
    @GetMapping
    public R list(ProcessDTO params) {
        return R.ok(processService.getProcessByPage(params));
    }

    @ApiOperation(value = "查询流程图", notes = "查询流程图")
    @Inner(value = false)
    @GetMapping(value = "/resource/{proInsId}/{procDefId}/{resType}")
    public ResponseEntity resourceRead(@PathVariable String procDefId, @PathVariable String proInsId, @PathVariable String resType) {

        HttpHeaders headers = new HttpHeaders();

        if (ResourceTypeEnum.XML.getType().equals(resType)) {
            headers.setContentType(MediaType.APPLICATION_XML);
        } else {
            headers.setContentType(MediaType.IMAGE_PNG);
        }

        InputStream resourceAsStream = processService.readResource(procDefId, proInsId, resType);
        return new ResponseEntity(IoUtil.readBytes(resourceAsStream), headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "失效流程(取消部署)", notes = "失效流程（取消部署）")
    @PutMapping("/status/{procDefId}/{status}")
    public R updateState(@PathVariable String procDefId, @PathVariable String status) {
        return R.ok(processService.updateStatus(status, procDefId));
    }

    @ApiOperation(value = "删除流程", notes = "删除流程")
    @DeleteMapping("/{deploymentId}")
    public R deleteProcIns(@PathVariable String deploymentId) {
        return R.ok(processService.removeProcIns(deploymentId));
    }


}
