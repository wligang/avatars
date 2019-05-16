package com.wlgdo.avatar.service.config.exceptions;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/16 23:24
 */
@Component
@ControllerAdvice
public class HandlerErrorException {

    @ExceptionHandler(RuntimeException.class) //拦截所有运行时的全局异常
    @ResponseBody
    public HashMap<String, Object> ErrorTest() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("500", "空指针异常");
        map.put("404", "地址错误");
        return map;
    }

}
