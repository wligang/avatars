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
import java.util.Set;

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

        redisTemplate.opsForHash().get("user", "hashKey");

        redisTemplate.opsForHash().put("user", "name", new CsdnUser("hash"));
        Map<String, CsdnUser> userMap = redisTemplate.opsForHash().entries("user");
        logger.info("{}", userMap);
        redisTemplate.opsForList().leftPush("userListKye", new CsdnUser("001"));

        redisTemplate.opsForList().leftPush("userListKye", new CsdnUser("002"));

        Object list = redisTemplate.opsForList().range("userListKye", 0, 10);

        logger.info("list-{}", list);


        // redisTemplate.opsForSet().add("userSet", list);
        Set userSet = redisTemplate.opsForSet().members("userSet");
        logger.info("{}", userSet);

        redisTemplate.opsForSet().add("userSet", new CsdnUser("set"));
        Set userSet1 = redisTemplate.opsForSet().members("userSet");
        logger.info("{}", userSet1);

        redisTemplate.opsForZSet().add("userList", new CsdnUser("s10"), 10d);
        redisTemplate.opsForZSet().add("userList", new CsdnUser("s13"), 13d);
        redisTemplate.opsForZSet().add("userList", new CsdnUser("s0"), 0d);
        redisTemplate.opsForZSet().add("userList", new CsdnUser("s6"), 6d);
        Set sortList = redisTemplate.opsForZSet().range("userList", 0, 10);
        logger.info("{}", sortList);
        return null;
    }


    public void sortedUserList(int size) {
        size = 10;
        Long startTime = System.currentTimeMillis();
        logger.info("Begin program:{}", startTime);
        redisTemplate.opsForZSet().add("userSet", 2, 0);
        Double score = redisTemplate.opsForZSet().score("userSet", 3);
        logger.info("score:{}", score);
        Long numSize = redisTemplate.opsForZSet().size("userSet");
        Set set = redisTemplate.opsForZSet().range("userSet", 0, 30);
        logger.info("First 30 :[{}]", set);
        logger.info("End program:{}[{}ms]", numSize, System.currentTimeMillis() - startTime);
    }
}
