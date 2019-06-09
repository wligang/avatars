package com.wlgdo.avatar.service.actors.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlgdo.avatar.common.http.HttpResp;
import com.wlgdo.avatar.dubbo.rpc.Resp;
import com.wlgdo.avatar.service.actors.entity.TActor;
import com.wlgdo.avatar.service.actors.serivce.ActorService;
import com.wlgdo.avatar.service.users.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/4 2:16
 */
@RestController
public class ActorsController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActorService actorService;

    @GetMapping("actors")
    public Object getUserInfo() {

        logger.info("Being get user :{}");
        List<TActor> list = actorService.list();
        return HttpResp.instance().setData(list);
    }

    @GetMapping("actors/{name}")
    public Object saveActor(@PathVariable String name) {
        TActor actor = new TActor();
        actor.setName(name);
        actor.setCreateTime(LocalDateTime.now());
        boolean ret = actorService.saveActor(actor);
        return HttpResp.instance().setData(ret);
    }


    @GetMapping("actors/list")
    public Object getActor() {
        IPage<TActor> page = new Page<>(1, 10);
        ((Page<TActor>) page).setOptimizeCountSql(true);
        QueryWrapper<TActor> wrapQuery = new QueryWrapper<TActor>();
        IPage<TActor> pageInfo = actorService.page(page, wrapQuery);
        return HttpResp.instance().setData(pageInfo);
    }
}
