package com.wlgdo.avatar.service.users.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.response.Result;
import com.wlgdo.avatar.service.users.entity.Users;
import com.wlgdo.avatar.service.users.mapper.UsersMapper;
import com.wlgdo.avatar.service.users.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ligang.Wang[wlgchun@163.com]
 * @since 2019-06-10
 */
@Slf4j
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Resource
    private UsersMapper userMapper;

    @DS("master")
    public String getUserName() {
        log.info("start get user name");
        Users user = userMapper.selectById(1L);
        return user == null ? "My name is feify" : user.getUserFrom();
    }

    @DS("master")
    public Result getUser() {
        log.info("get user");
        PageInfo list = getList(0, 10);
        log.info("{}", list);
        return Result.newSuccess(list);
    }

    @DS("slave_1")
    public PageInfo getList(int pageIndex, int pageSize) {
        log.info("stat get user list");
        PageInfo pageInfo = this.getList(pageIndex, pageSize);
        return pageInfo;
    }
}
