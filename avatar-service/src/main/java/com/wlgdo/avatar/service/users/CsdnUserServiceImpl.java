package com.wlgdo.avatar.service.users;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wlgdo.avatar.service.dao.CsdnUserMapper;
import com.wlgdo.avatar.service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/10 18:28
 */
@Service
public class CsdnUserServiceImpl {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private CsdnUserMapper csdnUserMapper;


    public List<User> getCsdnUserList() {

        User user = new User();
        Wrapper<User> userWrapper = new QueryWrapper(user);
        List<User> list = csdnUserMapper.selectList(userWrapper);

        Page<User> page = new Page<>(1, 5);
        IPage<User> userIPage = csdnUserMapper.selectPage(page, new QueryWrapper<User>()
                .eq("name", "大山"));
        logger.info("get user list:{}，{}", list, userIPage);

        return list;
    }

    public User addCsdnUser(User user) {
        logger.info("start insert with mybaties++:{}", user);

        Integer ret = csdnUserMapper.insert(user);

        logger.info("inserted result:{} ,{}", ret, user);
        return user;
    }


}
