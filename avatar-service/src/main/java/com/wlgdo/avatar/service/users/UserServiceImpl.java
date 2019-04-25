package com.wlgdo.avatar.service.users;


import com.alibaba.dubbo.config.annotation.Service;
import com.wlgdo.avatar.api.service.IUserService;

import com.wlgdo.avatar.service.dao.UserMapper;
import com.wlgdo.avatar.service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service
public class UserServiceImpl implements IUserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getUserName() {
        User user = userMapper.findUser();
        logger.info("user:{}", user);
        return user == null ? "My name is feify" : user.getName();
    }
}
