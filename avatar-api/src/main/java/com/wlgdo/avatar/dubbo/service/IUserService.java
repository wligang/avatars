package com.wlgdo.avatar.dubbo.service;


import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.response.Result;

public interface IUserService<T> {
    Result<T> getUser();

    PageInfo getList(int pageIndex, int pageSize);

    Object addCsdnUserAccount();
}
