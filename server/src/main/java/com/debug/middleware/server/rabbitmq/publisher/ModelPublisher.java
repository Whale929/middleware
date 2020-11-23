package com.debug.middleware.server.rabbitmq.publisher;

import com.debug.middleware.server.rabbitmq.entity.EventInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Author: AlexHsiao
 * @Date: 2020/11/21 下午3:57
 */
@Component
public class ModelPublisher {

    private static final Logger log= LoggerFactory.getLogger(ModelPublisher.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    public void sendMsg(EventInfo info){
        if(info!=null){
            try{
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.fanout.exchange.name"));
                Message msg=MessageBuilder.withBody(objectMapper.writeValueAsBytes(info)).build();
                rabbitTemplate.convertAndSend(msg);
                log.info("消息模型 fanoutExchange-生产者-发送消息:{}",info);
            } catch (JsonProcessingException e) {
                log.error("消息模型 fanoutExchange-生产者-发送消息:{}",info,e.fillInStackTrace());
            }
        }
    }
}