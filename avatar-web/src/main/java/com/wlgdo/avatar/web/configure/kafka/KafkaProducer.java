package com.wlgdo.avatar.web.configure.kafka;

import com.alibaba.fastjson.JSON;
import com.wlgdo.avatar.web.configure.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 这是一个失败的案例，我之所以这么写纯粹是为了实现单例模式和验证我写的springutll
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/16 22:02
 */

@Component
public class KafkaProducer {

    public static KafkaTemplate kafkaTemplate = null;

    public static volatile KafkaProducer Instance = null;

    private KafkaProducer() {
    }

    public static KafkaProducer instance() {
        if (Instance == null) {
            Instance = new KafkaProducer();
        }
        kafkaTemplate = SpringUtil.getBean(KafkaTemplate.class);
        return Instance;
    }

    static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public static final String AVATAR_TOPIC = "wlgdo-avatar";


    public boolean send(Object object) {
        String jsObject = JSON.toJSON(object).toString();
        logger.info("Begin sent Kafka message:{}", jsObject);
        ListenableFuture future = kafkaTemplate.send(AVATAR_TOPIC, jsObject);
        future.addCallback(f -> System.out.println("消息发送成功"), throwable -> System.out.println("消息发送失败"));
        return false;
    }

}
