package com.wlgdo.avatar.quartz.users.service.impl;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    }

    @Test
    public void getList() {
    }


}