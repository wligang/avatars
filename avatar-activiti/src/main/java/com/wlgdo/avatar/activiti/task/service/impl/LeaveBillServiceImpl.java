
package com.wlgdo.avatar.activiti.task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wlgdo.avatar.activiti.task.entity.LeaveBill;
import com.wlgdo.avatar.activiti.task.mapper.LeaveBillMapper;
import com.wlgdo.avatar.activiti.task.service.LeaveBillService;
import org.springframework.stereotype.Service;


@Service("leaveBillService")
public class LeaveBillServiceImpl extends ServiceImpl<LeaveBillMapper, LeaveBill> implements LeaveBillService {

}
