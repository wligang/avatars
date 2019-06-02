package com.wlgdo.avatar.web.apps.controller;

import com.alibaba.fastjson.JSONObject;
import com.wlgdo.avatar.dubbo.rpc.Resp;
import com.wlgdo.avatar.dubbo.rpc.apps.AppInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/3 23:04
 */
@RestController
public class AppManagerController {
    private static final String APP_NAME_KEY = "appName_";

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("apps/info/{key}")
    public Object index(@PathVariable String key) {
        JSONObject respData = new JSONObject();
        if (stringRedisTemplate.hasKey(APP_NAME_KEY + key)) {
            JSONObject jsonObject = new JSONObject().parseObject(stringRedisTemplate.opsForValue().get(APP_NAME_KEY + key));
            logger.info("userInfo:{}", jsonObject);
            respData.put("appInfo", jsonObject);
        }
        if (stringRedisTemplate.hasKey("userInfo_LeegooWang")) {
            JSONObject hotArticles = new JSONObject().parseObject(stringRedisTemplate.opsForValue().get("userInfo_LeegooWang"));
            respData.put("hotArticles", hotArticles.get("articles"));
        }
        return new Resp(respData);
    }

    @PostMapping("apps/info")
    public Object save(@RequestBody AppInfo app) {
        logger.info("保存app的设置：{}", app);
        stringRedisTemplate.opsForValue().set(APP_NAME_KEY + app.getAppKey(), JSONObject.toJSONString(app));
        return new Resp(app);
    }
}
