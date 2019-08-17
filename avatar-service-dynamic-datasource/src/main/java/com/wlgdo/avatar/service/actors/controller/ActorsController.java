package com.wlgdo.avatar.service.actors.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wlgdo.avatar.common.http.HttpResp;
import com.wlgdo.avatar.service.actors.entity.TActor;
import com.wlgdo.avatar.service.actors.serivce.ActorService;
import jdk.net.SocketFlow;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/4 2:16
 */
@Slf4j
@RestController
@AllArgsConstructor
public class ActorsController {

    private final ActorService actorService;

    @GetMapping("actors")
    public Object getUserInfo() {
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
        LambdaQueryWrapper<TActor> wrapQuery = new LambdaQueryWrapper<>();
        wrapQuery.eq(TActor::getStatus, SocketFlow.Status.OK);
        List<TActor> list = actorService.list(wrapQuery);
        Long orgId = 0L;
        //传统
        AtomicBoolean flag = new AtomicBoolean(false);
        list.forEach(a -> {
            if (orgId.equals(a.getId())) {
                flag.set(true);
                return;
            }
        });
        //Lambda
        boolean b = list.stream().anyMatch(a -> orgId.equals(a.getId()));

        return HttpResp.instance().setData(list);
    }
}
