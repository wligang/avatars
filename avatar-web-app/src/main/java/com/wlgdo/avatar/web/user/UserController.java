package com.wlgdo.avatar.web.user;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static String CSDN_USER = "userInfo_";

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("index/{key}")
    public Object index(@PathVariable String key) {

        if (stringRedisTemplate.hasKey(CSDN_USER + key)) {
            JSONObject jsonObject = new JSONObject().parseObject(stringRedisTemplate.opsForValue().get(CSDN_USER + key));
            logger.info("userInfo:{}", jsonObject);
            return jsonObject;
        }

        return String.format("未获取到用户【%s】授权，暂时无法获取数据", key);
    }

    @GetMapping("index/get/{key}")
    public Object getUserInfo(@PathVariable String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return value == null ? "noValue" : value;
    }
}
