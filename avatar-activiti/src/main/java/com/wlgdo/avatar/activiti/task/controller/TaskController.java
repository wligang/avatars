

package com.wlgdo.avatar.activiti.task.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.wlgdo.avatar.activiti.dto.ActBillDTO;
import com.wlgdo.avatar.activiti.dto.CommentDto;
import com.wlgdo.avatar.activiti.dto.TaskDTO;
import com.wlgdo.avatar.activiti.task.service.ActTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.history.HistoricActivityInstance;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.InputStream;
import java.util.List;

/**
 * @author Ligang.Wang[@foxjk.com]
 * @date 2019-10-4 11:51:45
 */
@Slf4j
@Api(value = "任务管理接口", tags = "任务管理接口")
@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final ActTaskService actTaskService;

    @ApiOperation(value = "分页查询隐患流程任务历史", notes = "分页查询隐患流程任务历史")
    @GetMapping("/his")
    public R<IPage<TaskDTO>> getTaskHis(TaskDTO taskDTO) {
        return R.ok(actTaskService.getTaskHisByUserName(taskDTO, "user-id"));
    }

    @ApiOperation(value = "分页查询待办列表", notes = "分页查询隐患流程任务历史")
    @GetMapping("/todo")
    public R<IPage<TaskDTO>> getTaskTodo(TaskDTO taskDTO) {
        return R.ok(actTaskService.getTaskByName(taskDTO, "username"));
    }

    @ApiOperation(value = "查询需要审批的任务信息", notes = "查询需要审批的任务信息")
    @GetMapping("/{id}")
    public R<ActBillDTO> getTaskById(@PathVariable String id) {
        return actTaskService.getTaskById(id);
    }

    @ApiOperation(value = "提交审批任务信息", notes = "提交审批任务信息")
    @PostMapping
    public R submitTask(@RequestBody @Valid ActBillDTO activitiBill) {
        return R.ok(actTaskService.submitTask(activitiBill));
    }

    @ApiOperation(value = "根据任务ID查询任务审批流程图", notes = "根据任务ID查询任务审批流程图")
    @GetMapping("/view/{id}")
    public ResponseEntity viewCurrentImage(@PathVariable String id) {
        InputStream imageStream = actTaskService.viewByTaskId(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity(IoUtil.readBytes(imageStream), headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "根据流程ID查询任务审批流程图", notes = "根据流程ID查询任务审批流程图")
    @GetMapping("/process/view/{id}")
    public ResponseEntity viewCurrentProcessImage(@PathVariable String processId) {
        InputStream imageStream = actTaskService.viewByProcessId(processId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity(IoUtil.readBytes(imageStream), headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "查询任务批注列表信息", notes = "查询任务批注列表信息")
    @GetMapping("/comment/{id}")
    public R<List<CommentDto>> getTaskCommentList(@PathVariable String id) {
        return R.ok(actTaskService.getCommentByTaskId(id));
    }

    @ApiOperation(value = "查询任务历史批注列表信息", notes = "查询任务历史批注列表信息")
    @GetMapping("/his/comment/{taskId}")
    public R<List<CommentDto>> getCommentList(@PathVariable String taskId) {
        return R.ok(actTaskService.getCommentHisByTaskId(taskId));
    }

    @ApiOperation(value = "根据流程ID查询审批历史记录", notes = "根据流程ID查询审批历史记录")
    @GetMapping("/processList/{proccessId}")
    public R<List<HistoricActivityInstance>> getTaksHisFlow(@PathVariable String proccessId) {
        return actTaskService.getTaskHisFlow(proccessId);
    }

}
