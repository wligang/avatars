package com.wlgdo.avatar.service.users.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wlgdo.avatar.service.users.entity.TActor;

import java.io.Serializable;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/13 22:08
 */
public interface ActorService extends IService<TActor> {

    boolean saveActor(TActor actor);


    TActor getActorById(Serializable id);
}
