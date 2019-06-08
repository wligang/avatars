package com.wlgdo.avatar.service.users.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.response.Result;
import com.wlgdo.avatar.service.actors.entity.TActor;

public interface UserService extends IService<TActor> {

    Result<TActor> getUser();

    PageInfo getList(int pageIndex, int pageSize);

   }
