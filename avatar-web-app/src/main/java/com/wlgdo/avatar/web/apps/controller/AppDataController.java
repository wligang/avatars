package com.wlgdo.avatar.web.apps.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wlgdo.avatar.common.utils.GZIPUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Set;

import static com.wlgdo.avatar.web.user.controller.UserController.CSDN_USER;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/7/14 22:03
 */
@RestController
public class AppDataController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("blog")
    public Object index(HttpServletResponse response) {

        List<JSONObject> lists = Lists.newArrayList();
        Set<String> keys = stringRedisTemplate.keys(CSDN_USER + "*");
        keys.stream().forEach(k -> {
            lists.add(JSONObject.parseObject(stringRedisTemplate.opsForValue().get(k)));
        });

        return GZIPUtils.compress(lists.toString());


    }


}
