package com.wlgdo.avatar.service.users.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlgdo.avatar.service.users.entity.TActor;
import com.wlgdo.avatar.service.users.mapper.TActorMapper;
import com.wlgdo.avatar.service.users.service.ActorService;
import org.springframework.stereotype.Service;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/13 22:12
 */
@Service
public class ActorServiceImpl extends ServiceImpl<TActorMapper, TActor> implements ActorService {
}
