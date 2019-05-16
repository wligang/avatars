package com.wlgdo.avatar.web.configure.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/16 22:02
 */


@Component
@EnableKafka
public class KafkaProducer {

    static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public static final String AVATAR_TOPIC = "wlgdo-avatar";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public boolean send(Object object) {
        logger.info("开始发送消息到Kafka");
        ListenableFuture future = kafkaTemplate.send(AVATAR_TOPIC, object);
        future.addCallback(f -> System.out.println("消息发送成功"), throwable -> System.out.println("消息发送失败"));
        return false;
    }

}
