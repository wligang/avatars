package com.wlgdo.avatar.service.users.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.response.Result;

import com.wlgdo.avatar.service.users.entity.Users;
import com.wlgdo.avatar.service.users.mapper.UsersMapper;
import com.wlgdo.avatar.service.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, Users> implements UserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UsersMapper userMapper;

    @DS("master")
    public String getUserName() {
        logger.info("start get user name");
        Users user = userMapper.selectById(1L);
        return user == null ? "My name is feify" : user.getUserFrom();
    }

    @DS("master")
    @Override
    public Result getUser() {
        logger.info("get user");
        PageInfo list = getList(0, 10);
        logger.info("{}", list);
        return Result.newSuccess(list);
    }

    @DS("slave_1")
    @Override
    public PageInfo getList(int pageIndex, int pageSize) {
        logger.info("stat get user list");
        PageInfo pageInfo = this.getList(pageIndex, pageSize);
        return pageInfo;
    }


}
