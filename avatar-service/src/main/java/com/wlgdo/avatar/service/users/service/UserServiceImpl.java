package com.wlgdo.avatar.service.users.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.rpc.Resp;
import com.wlgdo.avatar.dubbo.service.IUserService;
import com.wlgdo.avatar.service.users.mapper.UserMapper;
import com.wlgdo.avatar.service.users.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Service
@Component
public class UserServiceImpl implements IUserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    @Override
    public String getUserName() {
        logger.info("start get user name");
        User user = userMapper.findUser();
        logger.error("this is a error log test:{}", System.currentTimeMillis());
        return user == null ? "My name is feify" : user.getName();
    }

    @Override
    public PageInfo getList(int pageIndex, int pageSize) {
        logger.info("stat get user list");
        List lists = userMapper.getList();
        PageInfo pageInfo = new PageInfo(lists);

        return pageInfo;
    }

    @Override
    public Object addCsdnUserAccount() {

        logger.info("stat add csdn user account");

        return new Resp("ok");
    }


}
