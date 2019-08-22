package com.wlgdo.avatar.web.apps.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        List<JSONObject> lists = new ArrayList();
        Set<String> keys = stringRedisTemplate.keys(CSDN_USER + "*");
        keys.stream().forEach(k -> {
            lists.add(JSONObject.parseObject(stringRedisTemplate.opsForValue().get(k)));
        });

        List<JSONObject> collect = stringRedisTemplate.keys(CSDN_USER + "*")
                .stream()
                .map(k -> JSONObject.parseObject(stringRedisTemplate.opsForValue().get(k)))
                .collect(Collectors.toList());


//        return GZIPUtils.compress(lists.toString());

        return collect;

    }


}
