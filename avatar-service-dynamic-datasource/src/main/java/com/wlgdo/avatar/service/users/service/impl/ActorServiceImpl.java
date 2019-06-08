package com.wlgdo.avatar.service.users.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.service.users.entity.TActor;
import com.wlgdo.avatar.service.users.mapper.TActorMapper;
import com.wlgdo.avatar.service.users.service.ActorService;
import com.wlgdo.avatar.service.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/13 22:12
 */
@Service
public class ActorServiceImpl extends ServiceImpl<TActorMapper, TActor> implements ActorService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public boolean saveActor(TActor actor) {
        logger.info("保存Actor：{}", actor);
        actor.setNickName("master");
        saveMaster(actor);
        logger.info("save slave:{}", LocalDateTime.now());
        actor.setNickName("slave_1");
        saveSlave1(actor);
        logger.info("save :{}", LocalDateTime.now());
        return true;
    }

    @Override
    public TActor getActorById(Serializable id) {
        TActor actor = getByIdMaster(id);
        PageInfo list = userService.getList(0, 10);
        logger.info("actor:{}", actor);
        logger.info("list:{}", list);
        return actor;
    }


    @DS("master")
    public boolean saveMaster(TActor entity) {
        logger.info("save slave:{}", entity);
        return super.save(entity);
    }

    @DS("slave")
    public boolean saveSlave1(TActor entity) {
        logger.info("save slave:{}", entity);
        return super.save(entity);
    }


    @DS("master")
    public TActor getByIdMaster(Serializable id) {
        return super.getById(id);
    }

    @DS("slave_1")
    public TActor getByIdSlave1(Serializable id) {
        return super.getById(id);
    }
}
