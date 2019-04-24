package com.wlgdo.avatar.service.users;

import com.wlgdo.avatar.api.IUserService;
import com.wlgdo.avatar.service.dao.UserMapper;
import com.wlgdo.avatar.service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getUserName() {
        User user = userMapper.findUser();
        logger.info("user:{}", user);
        return "My name is feify";
    }
}
