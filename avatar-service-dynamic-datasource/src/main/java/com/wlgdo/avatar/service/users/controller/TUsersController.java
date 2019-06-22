package com.wlgdo.avatar.service.users.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlgdo.avatar.common.http.HttpResp;

import com.wlgdo.avatar.service.actors.entity.TActor;
import com.wlgdo.avatar.service.bridge.AuthorUserService;
import com.wlgdo.avatar.service.bridge.BridgeBuilder;
import com.wlgdo.avatar.service.bridge.HidoUserService;
import com.wlgdo.avatar.service.users.entity.TUsers;
import com.wlgdo.avatar.service.users.service.ITUsersService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Ligang.Wang[wlgchun@163.com]
 * @since 2019-06-10
 */
@RestController
public class TUsersController {

    static Logger logger = LoggerFactory.getLogger(TUsersController.class);

    @Autowired
    private ITUsersService itUsersService;

    @GetMapping("/users")
    public Object getUserList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        BridgeBuilder bridgeBuilder = new BridgeBuilder();
        bridgeBuilder.setUserInterface(new AuthorUserService());
        bridgeBuilder.save("作者:李");
        bridgeBuilder.setUserInterface(new HidoUserService());
        bridgeBuilder.save("平台：李");
        IPage<TUsers> page = new Page<>(pageIndex, pageSize);
        Wrapper<TUsers> queryWrapper = new QueryWrapper<>();
        //((QueryWrapper<TUsers>) queryWrapper).like("nick_name", "wlgdo");
        IPage<TUsers> pageData = itUsersService.page(page, queryWrapper);

        return HttpResp.instance().setData(pageData);
    }

    @GetMapping("/users/list")
    public Object getList(@RequestParam(required = false) String nickName, @RequestParam(required = false) String mobile) {

        QueryWrapper queryWrapper = new QueryWrapper<TUsers>();
        if (StringUtils.isNotBlank(nickName)) {
            queryWrapper.like("nick_name", nickName);
        }
        if (StringUtils.isNotBlank(mobile)) {
            queryWrapper.like("contact_number", mobile);
        }
        List<TUsers> userlist = itUsersService.list(queryWrapper);

        List<TUsers> list = userlist.stream().filter(e -> e.getSex() == 1).collect(Collectors.toList());

        List list1 = BeanMapper.mapList(list, TActor.class);

        List<String> openIds = list.stream().map(tUsers -> tUsers.getOpenId()).collect(Collectors.toList());

        DozerBeanMapper mapper = new DozerBeanMapper();

        List<Class<TActor>> aList = list.stream().map(e -> TActor.class).collect(Collectors.toList());

        Optional<TUsers> firstUser = list.stream().findFirst();
        TActor actor = new TActor();
        try {
            BeanUtils.copyProperties(firstUser.get(), actor);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return HttpResp.instance().setData(userlist);
    }


}

