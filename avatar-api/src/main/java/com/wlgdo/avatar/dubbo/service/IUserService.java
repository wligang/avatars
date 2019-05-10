package com.wlgdo.avatar.dubbo.service;


import com.wlgdo.avatar.dubbo.common.PageInfo;

public interface IUserService {
    String getUserName();

    PageInfo getList(int pageIndex, int pageSize);

    Object addCsdnUserAccount();
}
