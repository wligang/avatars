package com.wlgdo.avatar.web.apps.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/8/18 23:10
 */
@Slf4j
@Controller
public class AppOauthController {

    private static String redirect_uri = "http://www.wlgdo.com/oauth?";
    private static String CLIENT_ID = "189881679c4983dd7ae6";
    private static String CLIENT_SECRET = "1fbf8e8f67933b28d075fc77af7c10a3a14d7f0d";
    private static String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token?";
//    private static String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token?client_id=189881679c4983dd7ae6&client_secret=1fbf8e8f67933b28d075fc77af7c10a3a14d7f0d&redirect_uri=http://www.wlgdo.com/oauth&code=2f5ca04906e841b4e8e3&state=213844c9-bbd6-4016-9b1c-94776a24e3a8";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("github")
    public Object callback(@RequestParam(required = false) String code, @RequestParam(required = false) String access_token,
                           @RequestParam(required = false) String scope,
                           @RequestParam(required = false) String state, HttpServletResponse response) throws IOException {

        log.info("获取到的token是: [{} , {}]", code, scope);
        String accessToken = null;
        if (StringUtils.isNotBlank(code)) {
            StringBuffer url = new StringBuffer(ACCESS_TOKEN_URL)
                    .append("client_id=").append(CLIENT_ID)
                    .append("&client_secret=").append(CLIENT_SECRET)
                    .append("&code=").append(code)
                    .append("&state=").append(state);
            String accessTokenStr = HttpUtil.get(url.toString());

            log.info("获取到的授权是：{}", accessTokenStr);
            accessToken = accessTokenStr.split("&")[0];
            if (accessToken != null && accessToken.indexOf("access_token") > -1 && accessToken != null) {
                stringRedisTemplate.opsForValue().set("access_token", accessToken);
                String redirectUrl = redirect_uri + "?" + accessToken;
                log.info("获取到的授权是：{}", redirectUrl);
                response.sendRedirect(redirectUrl);
            }
            return "授权失败" + accessTokenStr;
        }
        if (StringUtils.isNotBlank(access_token)) {
            stringRedisTemplate.opsForValue().set("access_token", accessToken);
            String redirectUrl = redirect_uri +  accessToken;
            log.info("获取到的授权是：{}", redirectUrl);
            response.sendRedirect(redirectUrl);
        }

        if (StringUtils.isNotBlank(access_token)) {
            stringRedisTemplate.opsForValue().set("access_token", accessToken);
        }
        return String.format("授权失败[%s]", code, access_token);
    }

}
