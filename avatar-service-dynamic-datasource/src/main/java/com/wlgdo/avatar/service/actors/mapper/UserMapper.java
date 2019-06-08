package com.wlgdo.avatar.service.actors.mapper;

import com.wlgdo.avatar.service.actors.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    User findUser();

    List getList();
}
