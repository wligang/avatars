package com.wlgdo.avatar.service.users.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlgdo.avatar.service.users.entity.TUsers;
import com.wlgdo.avatar.service.users.mapper.TUsersMapper;
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
public class TUsersServiceImpl extends ServiceImpl<TUsersMapper, TUsers> implements IUsersService {

}
