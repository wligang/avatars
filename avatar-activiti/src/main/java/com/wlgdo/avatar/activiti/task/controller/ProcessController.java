

package com.wlgdo.avatar.activiti.task.controller;

import cn.hutool.core.io.IoUtil;
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
