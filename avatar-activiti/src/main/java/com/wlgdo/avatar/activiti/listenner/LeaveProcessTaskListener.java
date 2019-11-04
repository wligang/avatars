package com.wlgdo.avatar.activiti.listenner;

import com.wlgdo.avatar.activiti.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ligang.Wang[@foxjk.com]
 */
@Slf4j
public class LeaveProcessTaskListener implements TaskListener {

    /**
     * 查询提交人的上级
     *
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setCategory("LEAVE");
        List<String> remindUserList = new ArrayList<>();
        delegateTask.addCandidateUser("userId-1");
        remindUserList.add("userId-1");
        SimpMessagingTemplate simpMessagingTemplate = SpringContextHolder.getBean(SimpMessagingTemplate.class);
        remindUserList.forEach(user -> {
            String target = String.format("%s-%s", "userId-1", "1");
            simpMessagingTemplate.convertAndSendToUser(target, "/remind", delegateTask.getName());
        });
    }
}
