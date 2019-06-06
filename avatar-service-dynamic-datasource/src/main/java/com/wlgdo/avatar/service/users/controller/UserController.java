package com.wlgdo.avatar.service.users.controller;

import com.wlgdo.avatar.common.http.HttpResp;
import com.wlgdo.avatar.dubbo.rpc.Resp;
import com.wlgdo.avatar.service.users.entity.TActor;
import com.wlgdo.avatar.service.users.service.ActorService;
import com.wlgdo.avatar.service.users.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/4 2:16
 */
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActorService actorService;


    @GetMapping("user/{id}")
    public Object getUserInfo(@PathVariable Serializable id) {

        logger.info("Being get user :{}", id);
        return new Resp(actorService.getById(id));
    }

    @GetMapping("actor/{name}")
    public Object saveActor(@PathVariable String name) {
        TActor actor = new TActor();
        actor.setNickName("华盛顿");
        actor.setCreateTime(LocalDateTime.now());
        actor.setMobile("15501033589");
        boolean ret = actorService.saveActor(actor);
        return HttpResp.instance().setData(ret);
    }


    @GetMapping("actor/get/{id}")
    public Object getActor(@PathVariable Serializable id) {
        return HttpResp.instance().setData(actorService.getActorById(id));
    }
}
