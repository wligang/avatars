package com.wlgdo.avatar.service.users.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.response.Result;
import com.wlgdo.avatar.service.actors.entity.TActor;
import com.wlgdo.avatar.service.actors.mapper.TActorMapper;
import com.wlgdo.avatar.service.users.mapper.UserMapper;
import com.wlgdo.avatar.service.users.entity.User;
import com.wlgdo.avatar.service.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<TActorMapper, TActor> implements UserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    @DS("master")
    public String getUserName() {
        logger.info("start get user name");
        User user = userMapper.findUser();
        return user == null ? "My name is feify" : user.getName();
    }


    @DS("master")
    @Override
    public Result getUser() {
        logger.info("get user");
        PageInfo list = getList(0, 10);
        logger.info("{}", list);
        Result<TActor> result = new Result<>();

        return result;
    }

    @DS("slave_1")
    @Override
    public PageInfo getList(int pageIndex, int pageSize) {
        logger.info("stat get user list");
        List lists = userMapper.getList();
        PageInfo pageInfo = new PageInfo(lists);

        return pageInfo;
    }


}
