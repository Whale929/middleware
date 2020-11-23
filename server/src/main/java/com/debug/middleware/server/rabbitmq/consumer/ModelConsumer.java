package com.debug.middleware.server.rabbitmq.consumer;

import com.debug.middleware.server.rabbitmq.entity.EventInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Author: AlexHsiao
 * @Date: 2020/11/21 下午7:33
 */
@Component
public class ModelConsumer {
    private static final Logger log=LoggerFactory.getLogger(ModelConsumer.class);

    @Autowired
    public ObjectMapper objectMapper;

    @RabbitListener(queues = "${mq.fanout.queue.one.name}",containerFactory = "singleListenerContainer")
    public void consumeFanoutMsgOne(@Payload byte[] msg){
        try{
            EventInfo info=objectMapper.readValue(msg,EventInfo.class);
            log.info("消息模型fanoutExchange-one-消费者-监听消息到消息:{}",info);
        }catch (Exception e){
            log.error("消息模型-消费者-发生异常:",e.fillInStackTrace());
        }
    }

    @RabbitListener(queues = "${mq.fanout.queue.two.name}",containerFactory = "singleListenerContainer")
    public void consumeFanoutMsgTwo(@Payload byte[] msg){
        try{
            EventInfo info=objectMapper.readValue(msg,EventInfo.class);
            log.info("消息模型fanoutExchange-two-消费者-监听消息到消息:{}",info);
        }catch (Exception e){
            log.error("消息模型-消费者-发生异常:",e.fillInStackTrace());
        }
    }
}
