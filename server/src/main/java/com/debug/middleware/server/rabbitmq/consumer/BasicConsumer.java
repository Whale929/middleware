package com.debug.middleware.server.rabbitmq.consumer;

import com.debug.middleware.server.rabbitmq.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.client.protocol.decoder.ObjectMapDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Author: AlexHsiao
 * @Date: 2020/11/20 上午10:49
 */
@Component
public class BasicConsumer {

    private static final Logger log= LoggerFactory.getLogger(BasicConsumer.class);

    @Autowired
    public ObjectMapper objectMapper;

    /**
     * 单一容器工厂实例
     */
    @RabbitListener(queues = "${mq.basic.info.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMsg(@Payload byte[] msg){
        try {
            String message=new String(msg,"utf-8");
            log.info("基本消息模型-消费者-间听到消费消息:{}",message);
        }catch (Exception e){
            log.error("",e.fillInStackTrace());
        }
    }

    /**
     * 单一工厂实例，处理对象信息
     */
    @RabbitListener(queues = "${mq.object.info.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeObjectMsg(@Payload Person person){
        try{
            log.info("基本消息模型-监听消费处理对象信息-消费者-监听消费到消息：{}",person);
        }catch (Exception e){
            log.error("基本消息模型-监听消费处理对象信息-消费者-发生异常：",e.fillInStackTrace());
        }
    }
}
