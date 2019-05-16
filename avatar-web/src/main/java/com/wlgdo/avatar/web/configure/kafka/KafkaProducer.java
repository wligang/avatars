package com.wlgdo.avatar.web.configure.kafka;

import com.alibaba.fastjson.JSON;
import com.wlgdo.avatar.web.configure.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/16 22:02
 */


@Component
public class KafkaProducer {

    public KafkaTemplate kafkaTemplate;

    public static volatile KafkaProducer Instance = null;

    public static KafkaProducer getInstance() {
        if (instance() == null) {
            Instance = new KafkaProducer();
            KafkaTemplate kafkaTemplate = SpringUtil.getBean(KafkaTemplate.class);
            instance().kafkaTemplate=kafkaTemplate;
        }
        return instance();
    }

    public KafkaProducer() {
    }

    public static KafkaProducer instance() {
        return new KafkaProducer();
    }

    static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public static final String AVATAR_TOPIC = "wlgdo-avatar";


    public boolean send(Object object) {
        String jsObject = JSON.toJSON(object).toString();
        logger.info("开始发送消息到Kafka:{}", jsObject);
        ListenableFuture future = kafkaTemplate.send(AVATAR_TOPIC, jsObject);
        future.addCallback(f -> System.out.println("消息发送成功"), throwable -> System.out.println("消息发送失败"));
        return false;
    }

}
