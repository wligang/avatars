package com.wlgdo.avatar.quartz.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlgdo.avatar.quartz.schedule.entity.QrtzCronTriggers;
import com.wlgdo.avatar.quartz.schedule.mapper.QrtzCronTriggersMapper;
import com.wlgdo.avatar.quartz.schedule.service.IQrtzCronTriggersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ligang.Wang[wlgchun@163.com]
 * @since 2019-06-29
 */
@Service
public class QrtzCronTriggersServiceImpl extends ServiceImpl<QrtzCronTriggersMapper, QrtzCronTriggers> implements IQrtzCronTriggersService {

}
