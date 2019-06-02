package com.wlgdo.avatar.web.apps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/2 23:48
 */
@Service
public class AppService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate redisTemplate;


    public boolean lock(long timeout, long expire, final TimeUnit unit) {
        logger.info("开始查询状态");
        long beginTime = System.nanoTime();  // 用nanos、mills具体看需求.
        timeout = TimeUnit.SECONDS.toNanos(timeout);
        try {
            // 在timeout的时间范围内不断轮询锁
            while (System.nanoTime() - beginTime < timeout) {
                // 锁不存在的话，设置锁并设置锁过期时间，即加锁
                if (redisTemplate.opsForValue().setIfAbsent(this.key, "1")) {
                    redisTemplate.expire(key, expire, unit);//设置锁失效时间, 防止永久阻塞
                    this.lock = true;
                    return true;
                }
                // 短暂休眠后轮询，避免可能的活锁
                System.out.println("get lock waiting...");
                Thread.sleep(30, RANDOM.nextInt(30));
            }
        } catch (Exception e) {
            throw new RuntimeException("locking error", e);
        }
        return false;
        return false;
    }

}
