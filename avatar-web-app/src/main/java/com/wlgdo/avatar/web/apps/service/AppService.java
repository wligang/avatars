package com.wlgdo.avatar.web.apps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/2 23:48
 */
@Service
public class AppService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate redisTemplate;


    public boolean locke() {
        logger.info("开始查询状态");

        return false;
    }

}
