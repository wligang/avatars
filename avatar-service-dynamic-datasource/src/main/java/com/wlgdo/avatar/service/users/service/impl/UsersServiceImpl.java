package com.wlgdo.avatar.service.users.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlgdo.avatar.service.users.entity.Users;
import com.wlgdo.avatar.service.users.mapper.UsersMapper;
import com.wlgdo.avatar.service.users.service.IUsersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ligang.Wang[wlgchun@163.com]
 * @since 2019-06-10
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
