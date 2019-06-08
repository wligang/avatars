package com.wlgdo.avatar.service.users.service.impl;

import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.response.Result;
import com.wlgdo.avatar.service.users.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/10 16:26
 */
@SpringBootTest
// 让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Before
    public void printStart() {
        System.out.println("#######################Begin Testting################################");
    }

    @After
    public void printEnd() {
        System.out.println("#######################Test end################################");
    }

    @Test
    public void getUserName() {
        Result userName = userService.getUser();
        logger.info("The test result:{}", userName);
    }

    @Test
    public void getList() {
        PageInfo page = userService.getList(0, 2);
        Assert.assertFalse(page == null);
        logger.info("{}", page);
    }


}