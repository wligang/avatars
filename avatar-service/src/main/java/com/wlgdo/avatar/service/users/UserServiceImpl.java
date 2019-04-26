package com.wlgdo.avatar.service.users;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.service.IUserService;
import com.wlgdo.avatar.service.dao.UserMapper;
import com.wlgdo.avatar.service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Service
@Component
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

    @Override
    public PageInfo getList(int pageIndex, int pageSize) {

        PageHelper.startPage(pageIndex, pageSize);
        List lists = userMapper.getList();
        PageInfo pageInfo = new PageInfo(lists);

        return pageInfo;
    }


}
