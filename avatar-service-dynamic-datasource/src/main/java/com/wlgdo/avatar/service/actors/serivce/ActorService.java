package com.wlgdo.avatar.service.actors.serivce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wlgdo.avatar.service.actors.entity.TActor;

import java.io.Serializable;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/13 22:08
 */
public interface ActorService extends IService<TActor> {

    boolean saveActor(TActor actor);


    TActor getActorById(Serializable id);
}
