package com.wlgdo.avatar.web.user;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static String CSDN_USER = "userInfo_";

    @Value("${python.data.script.path}")
    private String pythonScriptPath;


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
        logger.info("开始获取用户{}的数据", key);
        try {
            String[] args1 = new String[]{"python", pythonScriptPath + "avatarBlog.py", key};
            Process pr = Runtime.getRuntime().exec(args1);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(), "utf8"));
            String line;
            while ((line = in.readLine()) != null) {
                logger.info("load data:{}", line);
            }
            in.close();
            pr.waitFor();
            logger.info("data load end", line);
        } catch (Exception e) {
            logger.error("获取数据异常了:{}", e);
            return "获取出错";
        }

        if (stringRedisTemplate.hasKey(CSDN_USER + key)) {
            JSONObject jsonObject = new JSONObject().parseObject(stringRedisTemplate.opsForValue().get(CSDN_USER + key));
            logger.info("userInfo:{}", jsonObject);
            return jsonObject;
        }

        return "不知道发生了啥";
    }
}
