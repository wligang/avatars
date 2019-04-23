package com.wlgdo.avatar.web.user;

import com.wlgdo.avatar.api.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public String getUserName() {
        return "My name is feify";
    }
}
