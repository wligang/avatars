package com.wlgdo.avatar.service.users.mapper;

import com.wlgdo.avatar.service.users.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    User findUser();

    List getList();
}
