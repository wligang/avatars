package com.wlgdo.avatar.service.users;

import com.wlgdo.avatar.service.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/10 18:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CsdnUserServiceImplTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CsdnUserServiceImpl csdnUserService;

    @Test
    public void getCsdnUserList() {
        Object list = csdnUserService.getCsdnUserList();
        logger.info("get list result:{}", list);
    }

    @Test
    public void inserCsdnUser() {

        User user = new User();
        user.setName("大山");
        User ret = csdnUserService.addCsdnUser(user);

        Assert.assertTrue(ret != null);
        logger.info("######ttest pass:{}#############", ret);
    }
}