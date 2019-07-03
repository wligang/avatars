package com.wlgdo.avatar.quartz.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlgdo.avatar.quartz.schedule.entity.QrtzLocks;
import com.wlgdo.avatar.quartz.schedule.mapper.QrtzLocksMapper;
import com.wlgdo.avatar.quartz.schedule.service.IQrtzLocksService;
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
public class QrtzLocksServiceImpl extends ServiceImpl<QrtzLocksMapper, QrtzLocks> implements IQrtzLocksService {

}
