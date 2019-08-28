package com.wlgdo.avatar.web.apps.controller;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/8/18 23:10
 */
@Slf4j
@Controller
public class AppOauthController {

    private static final String REDIRECT_URI = "http://www.wlgdo.com/oauth";
    private static final String CLIENT_ID = "189881679c4983dd7ae6";
    private static final String CLIENT_SECRET = "1fbf8e8f67933b28d075fc77af7c10a3a14d7f0d";
    private static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token?";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * github 授权得回调地址
     *
     * @param code  授权码
     * @param state 参数
     */
    @RequestMapping("github")
    public Object callback(@RequestParam(required = false) String code,
                           @RequestParam(required = false) String state,
                           HttpServletResponse response) throws IOException {
        log.info("开始获取github授权信息:code: {},code :{}", code, state);
        response.sendRedirect("http://img.wlgdo.com/avatar/index.html?code=" + code + "&state=" + state + "&t=" + System.currentTimeMillis() / 1000);
        log.info("授权信息发送完毕:code: {},state :{}", code, state);
        return null;
    }

    /**
     * @param code         授权码
     * @param state        参数
     * @param scope        值域
     * @param access_token token
     */
    @Deprecated
    public Object callback(@RequestParam(required = false) String code,
                           @RequestParam(required = false) String state,
                           @RequestParam(required = false) String scope,
                           @RequestParam(required = false) String access_token,
                           HttpServletResponse response) throws IOException {

        log.info("获取到的token是: [{} , {}]", code, scope);
        if (StringUtils.isNotBlank(code)) {
            StringBuffer url = new StringBuffer(ACCESS_TOKEN_URL)
                    .append("client_id=").append(CLIENT_ID)
                    .append("&client_secret=").append(CLIENT_SECRET)
                    .append("&code=").append(code)
                    .append("&state=").append(state);
            String accessTokenStr = HttpUtil.get(url.toString());

            log.info("获取到的授权是：{}", accessTokenStr);
            String accessToken = accessTokenStr.split("&")[0];
            if (accessToken != null && accessToken.contains("access_token") && accessToken != null) {
                stringRedisTemplate.opsForValue().set("access_token", accessToken);
                String redirectUrl = REDIRECT_URI + "?" + accessToken;
                log.info("获取到的授权是：{}", redirectUrl);
//                response.sendRedirect("index.html?" + accessToken);
                response.sendRedirect("http://img.wlgdo.com/avatar/index.html?" + accessToken + "&t=" + System.currentTimeMillis() / 1000);
                return null;
            }
            return "授权失败" + accessTokenStr;
        }
        if (StringUtils.isNotBlank(access_token)) {
            stringRedisTemplate.opsForValue().set("access_token", access_token);
            String redirectUrl = REDIRECT_URI + "?access_token=" + access_token;
            log.info("获取到的授权是：{}", redirectUrl);
            response.sendRedirect("http://img.wlgdo.com/avatar/index.html?access_token=" + access_token + "&t=" + System.currentTimeMillis() / 1000);
            return null;
        }

        return String.format("授权失败 code:【%s】，access_token:【%s】", code, access_token);
    }

}
