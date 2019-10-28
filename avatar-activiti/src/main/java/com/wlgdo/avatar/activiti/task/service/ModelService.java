

package com.wlgdo.avatar.activiti.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.activiti.engine.repository.Model;

import java.util.List;
import java.util.Map;


public interface ModelService {
    public static final String PROCESS_PREFIX_KEY = "PT%d%s";
    public static final String TICKET_PREFIX_KEY = "PK%s";
    public static final String TICKET_CATEGORY_KEY = "TICKET";

    /**
     * 创建流程
     *
     * @param name
     * @param key
     * @param desc
     * @param category
     * @return
     */
    Model create(String name, String key, String desc, String category);

    Model copy(String templateId, String name, String desc);

    /**
     * 分页获取流程
     *
     * @param params
     * @return
     */
    IPage<Model> getModelPage(ProcessDTO params);

    /**
     * 删除流程
     *
     * @param id
     * @return
     */
    Boolean removeModelById(String id);

    /**
     * 部署流程
     *
     * @param id
     * @return
     */
    Boolean deploy(String id);


    List<Model> getModelList(ProcessDTO params);

    IPage<Model> getTicketModelPage(ProcessDTO params);
}
