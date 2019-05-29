package com.wlgdo.avatar.web.user.service;

import com.wlgdo.avatar.web.user.domain.CsdnUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/29 0:53
 */
@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;

    public List getUserOrderList() {

        CsdnUser csdnUser = new CsdnUser();
        csdnUser.setNickname("test");
        redisTemplate.opsForHash().get("user", "hashKey");

        redisTemplate.opsForHash().put("user", csdnUser, csdnUser);
        Map<String, CsdnUser> userMap = redisTemplate.opsForHash().entries("user");
        logger.info("{}", userMap);

        redisTemplate.opsForList().set("userListKye",0,csdnUser);

        return null;
    }

}
