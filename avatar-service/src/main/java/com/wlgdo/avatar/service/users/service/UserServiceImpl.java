package com.wlgdo.avatar.service.users.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.response.Result;
import com.wlgdo.avatar.dubbo.rpc.Resp;
import com.wlgdo.avatar.dubbo.service.IUserService;
import com.wlgdo.avatar.dubbo.users.User;
import com.wlgdo.avatar.service.users.mapper.UserMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Service
@Component
public class UserServiceImpl implements IUserService<User> {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<User> getUser() {
        logger.info("begin get User");
        com.wlgdo.avatar.service.users.entity.User entity = userMapper.findUser();
        User user=new User();
        BeanUtils.copyProperties(entity,user);
        user.setName(entity.getName());
        return Result.newSuccess(user);
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
