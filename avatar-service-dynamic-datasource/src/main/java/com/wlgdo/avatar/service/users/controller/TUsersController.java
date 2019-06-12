package com.wlgdo.avatar.service.users.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlgdo.avatar.dubbo.rpc.Resp;
import com.wlgdo.avatar.service.bridge.AuthorUser;
import com.wlgdo.avatar.service.bridge.BridgeBuilder;
import com.wlgdo.avatar.service.bridge.HidoUser;
import com.wlgdo.avatar.service.users.entity.TUsers;
import com.wlgdo.avatar.service.users.service.ITUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Ligang.Wang[wlgchun@163.com]
 * @since 2019-06-10
 */
@RestController
@RequestMapping("/users/")
public class TUsersController {

    @Autowired
    private ITUsersService itUsersService;

    @GetMapping("/{pageIndex}/{pageSize}")
    public Object getList(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {

        BridgeBuilder bridgeBuilder = new BridgeBuilder();
        bridgeBuilder.setUserInterface(new AuthorUser());
        bridgeBuilder.save("作者:李");
        bridgeBuilder.setUserInterface(new HidoUser());
        bridgeBuilder.save("平台：李");
        IPage<TUsers> page = new Page<>(pageIndex, pageSize);
        Wrapper<TUsers> queryWrapper = new QueryWrapper<>();
        //((QueryWrapper<TUsers>) queryWrapper).like("nick_name", "wlgdo");
        IPage<TUsers> pageData = itUsersService.page(page, queryWrapper);

        return new Resp(pageData);
    }

}

