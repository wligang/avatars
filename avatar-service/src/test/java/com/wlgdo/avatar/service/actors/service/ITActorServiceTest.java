package com.wlgdo.avatar.service.actors.service;


import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/11 13:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ITActorServiceTest {

    Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    private ITActorService actorServiceImpl;
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//
//    @Test
//    public void testGetById() {
//        TActor user = actorServiceImpl.getById("00");
//        logger.info("the getById test result:{}", user);
//        Assert.assertTrue(user != null);
//
//        logger.info("this test pass");
//    }
//
//
//    @Test
//    public void getFindUser() {
//        User user = actorServiceImpl.findActorUser();
//        logger.info("The test result:{}", user);
//    }
}