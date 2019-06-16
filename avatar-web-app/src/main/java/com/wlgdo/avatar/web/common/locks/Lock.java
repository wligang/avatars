package com.wlgdo.avatar.web.common.locks;

import com.wlgdo.avatar.web.common.enums.ELockKeyNameSpace;

import java.lang.annotation.*;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/17 0:46
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {
    /**
     * 分布式锁的key前缀
     */
    ELockKeyNameSpace lockKeyPrefix();

    String description();

    /**
     * 等待超时单位：秒
     */
    int timeOut() default 5;

    /**
     * 过期时长单位：秒
     */
    int expireTime() default 3;

}
