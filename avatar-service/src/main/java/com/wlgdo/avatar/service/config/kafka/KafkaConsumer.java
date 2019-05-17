package com.wlgdo.avatar.service.config.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/5/16 22:02
 */

@Component
@EnableScheduling
public class KafkaConsumer {

    Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = {"wlgdo-avatar"})
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        logger.info(">>>>>>>>>> Begin consume kafka message :{}", kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            logger.info(">>>>>>>>>> Begin consume kafka message :{}", message);
        }
    }


}
