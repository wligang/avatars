package com.wlgdo.avatar.service.users;


import com.alibaba.dubbo.config.annotation.Service;
import com.wlgdo.avatar.api.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Service
public class UserServiceImpl implements IUserService {

    Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    private UserMapper userMapper;

    @Override
    public String getUserName() {
//        User user = userMapper.findUser();
//        logger.info("user:{}", user);
        return "My name is feify";
    }
}
