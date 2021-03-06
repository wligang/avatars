package com.wlgdo.avatar.activiti.task.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.wlgdo.avatar.activiti.dto.ModelForm;
import com.wlgdo.avatar.activiti.dto.ProcessDTO;
import com.wlgdo.avatar.activiti.task.service.ModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.wlgdo.avatar.activiti.task.service.ModelService.PROCESS_PREFIX_KEY;
import static com.wlgdo.avatar.activiti.task.service.ModelService.TICKET_PREFIX_KEY;


/**
 * @author lengleng
 * @date 2018/9/25
 */
@Slf4j
@Api(value = "流程模型管理接口", tags = "流程模型管理接口")
@RestController
@RequestMapping("/model")
@AllArgsConstructor
public class ModelController {
    private final ModelService modelService;


    @ApiOperation(value = "创建流程模型管理接口", notes = "创建流程模型管理接口")
    @PostMapping(value = "/insert")
    public R<Boolean> insertForm(@RequestBody @Valid ModelForm form) {
        String modelKey = String.format(PROCESS_PREFIX_KEY, "0", form.getCategory());

        modelService.create(form.getName(), modelKey, form.getDesc(), form.getCategory());
        return R.ok(Boolean.TRUE);
    }


    @ApiOperation(value = "复制流程模型管理接口", notes = "复制流程模型管理接口")
    @PostMapping(value = "/copy")
    public R<Boolean> copyModel(@RequestBody @Valid ModelForm form) {
        modelService.copy(form.getTemplateId(), form.getName(), form.getDesc());
        return R.ok(Boolean.TRUE);
    }

    @ApiOperation(value = "保存流程模型接口", notes = "保存流程模型接口")
    @PostMapping
    public R createModel(@RequestParam String name, @RequestParam String key,
                         @RequestParam String desc, @RequestParam String category) {
        return R.ok(modelService.create(name, key, desc, category));
    }

    @ApiOperation(value = "分页查询流程模型列表", notes = "分页查询流程模型列表")
    @GetMapping
    public R getModelPage(ProcessDTO params) {
        return R.ok(modelService.getModelPage(params));
    }

    @ApiOperation(value = "分页查询作业票流程模型列表", notes = "分页查询作业票流程模型列表")
    @GetMapping("/ticket")
    public R getTicketModelPage(ProcessDTO params) {
        return R.ok(modelService.getTicketModelPage(params));
    }


    @ApiOperation(value = "查询流程模型列表", notes = "删除流程模型")
    @GetMapping("/list")
    public R getModelList(ProcessDTO params) {


        return R.ok(modelService.getModelList(params));
    }

    @ApiOperation(value = "删除流程模型", notes = "删除流程模型")
    @DeleteMapping("/{id}")
    public R removeModelById(@PathVariable("id") String id) {
        return R.ok(modelService.removeModelById(id));

    }

    @ApiOperation(value = "发布流程模型", notes = "发布流程模型")
    @PostMapping("/deploy/{id}")
    public R deploy(@PathVariable("id") String id) {
        return R.ok(modelService.deploy(id));
    }
}
