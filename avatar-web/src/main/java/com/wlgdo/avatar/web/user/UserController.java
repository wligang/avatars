package com.wlgdo.avatar.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wlgdo.avatar.api.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(lazy = false)
    private IUserService userService;

    @RequestMapping("index")
    public Object index() {
        String uname =userService.getUserName();

        logger.info("user`s name is:{}", uname);
        return "helloworld";
    }
}
