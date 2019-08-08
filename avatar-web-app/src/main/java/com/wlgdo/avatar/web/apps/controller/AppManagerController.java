package com.wlgdo.avatar.web.apps.controller;

import com.alibaba.fastjson.JSONObject;
import com.wlgdo.avatar.dubbo.rpc.Resp;
import com.wlgdo.avatar.dubbo.rpc.apps.AppInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/3 23:04
 */
@Slf4j
@RestController
public class AppManagerController {
    private static final String APP_NAME_KEY = "appName_";


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("apps/info/{key}")
    public Object index(@PathVariable String key) {
        log.info("获取应用信息:{}", key);
        JSONObject respData = new JSONObject();
        if (stringRedisTemplate.hasKey(APP_NAME_KEY + key)) {
            JSONObject jsonObject = JSONObject.parseObject(stringRedisTemplate.opsForValue().get(APP_NAME_KEY + key));
            log.info("userInfo:{}", jsonObject);
            respData.put("appInfo", jsonObject);
        }
        if (stringRedisTemplate.hasKey("userInfo_LeegooWang")) {
            JSONObject hotArticles = JSONObject.parseObject(stringRedisTemplate.opsForValue().get("userInfo_LeegooWang"));
            respData.put("hotArticles", hotArticles.get("articles"));
        }
        return new Resp(respData);
    }

    @PostMapping("apps/info")
    public Object save(@RequestBody AppInfo app) {
        log.info("保存app的设置：{}", app);
        stringRedisTemplate.opsForValue().set(APP_NAME_KEY + app.getAppKey(), JSONObject.toJSONString(app));
        return new Resp(app);
    }
}
