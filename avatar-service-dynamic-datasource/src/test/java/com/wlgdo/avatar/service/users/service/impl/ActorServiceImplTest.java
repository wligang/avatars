package com.wlgdo.avatar.service.users.service.impl;

import com.wlgdo.avatar.service.users.entity.TActor;
import com.wlgdo.avatar.service.users.entity.User;
import com.wlgdo.avatar.service.users.service.ActorService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/13 22:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActorServiceImplTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActorService actorService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getActor() {
        List<TActor> list = actorService.list();
        logger.info("the test result:{}", list);
    }
}