package com.wlgdo.avatar.service.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/12 23:30
 */
public class HidoUserService implements UserInterface {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object save(Object o) {
        logger.info("保存平台用户:{}", o);
        return o;
    }
}
