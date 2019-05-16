package com.wlgdo.avatar.web.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wlgdo.avatar.common.http.HttpResp;
import com.wlgdo.avatar.dubbo.common.PageInfo;
import com.wlgdo.avatar.dubbo.rpc.Resp;
import com.wlgdo.avatar.dubbo.service.IUserService;
import com.wlgdo.avatar.web.configure.kafka.KafkaProducer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private  KafkaTemplate kafkaTemplate;

    Logger logger = LoggerFactory.getLogger(getClass());

    //private RedisTemplate<String, String> redisTemplate;

    @Reference
    private IUserService userService;

    @RequestMapping("index")
    public Object index() {
        String uname = userService.getUserName();

        logger.info("user`s name is:{}", uname);
        return "welcome to you:" + (StringUtils.isEmpty(uname) ? "helloworld" : uname);
    }

    @RequestMapping("list/{pageNo}/{pageSize}")
    public Object list(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo page = userService.getList(pageNo, pageSize);
        return page;
    }

    @GetMapping("msg/{msg}")
    public Object getMessage(@PathVariable String msg) {
        boolean isOk = false;
        if (StringUtils.isNotBlank(msg)) {

            kafkaTemplate.send("wlgdo-avatar", "helloworld:" + msg);

        }
        return new HttpResp(isOk);
    }
}
