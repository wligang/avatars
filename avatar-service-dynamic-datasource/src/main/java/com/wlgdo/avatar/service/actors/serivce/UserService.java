package com.wlgdo.avatar.service.actors.serivce;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.response.Result;
import com.wlgdo.avatar.service.users.entity.TActor;

public interface UserService extends IService<TActor> {

    Result<TActor> getUser();

    PageInfo getList(int pageIndex, int pageSize);

    Object addCsdnUserAccount();
}
