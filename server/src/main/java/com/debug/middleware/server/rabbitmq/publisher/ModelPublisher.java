package com.debug.middleware.server.rabbitmq.publisher;

import com.debug.middleware.server.rabbitmq.entity.EventInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Strings;
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

    private static final Logger log = LoggerFactory.getLogger(ModelPublisher.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    /**
     * FanoutExchange
     *
     * @param info
     */
    public void sendMsgFanout(EventInfo info) {
        if (info != null) {
            try {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.fanout.exchange.name"));
                Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(info)).build();
                rabbitTemplate.convertAndSend(msg);
                log.info("消息模型 fanoutExchange-生产者-发送消息:{}", info);
            } catch (JsonProcessingException e) {
                log.error("消息模型 fanoutExchange-生产者-发送消息:{}", info, e.fillInStackTrace());
            }
        }
    }

    /**
     * DirectExchange-one
     *
     * @param info
     */
    public void sendMsgDirectOne(EventInfo info) {
        if (info != null) {
            try {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.direct.exchange.name"));
                rabbitTemplate.setRoutingKey(env.getProperty("mq.direct.routing.key.one.name"));
                Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(info)).build();
                rabbitTemplate.convertAndSend(msg);

                log.info("消息模型DirectExchange-one-生产者发送消息：{}", info);
            } catch (JsonProcessingException e) {
                e.printStackTrace();

                log.error("消息模型DirectExchange-one-生产者发送消息异常:{}", info, e.fillInStackTrace());
            }
        }
    }

    /**
     * DirectExchange-two
     *
     * @param info
     */
    public void sendMsgDirectTwo(EventInfo info) {
        if (info != null) {
            try {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.direct.exchange.name"));
                rabbitTemplate.setRoutingKey(env.getProperty("mq.direct.routing.key.two.name"));
                Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(info)).build();
                rabbitTemplate.convertAndSend(msg);

                log.info("消息模型DirectExchange-two-生产者发送消息：{}", info);
            } catch (JsonProcessingException e) {
                e.printStackTrace();

                log.error("消息模型DirectExchange-two-生产者发送消息异常:{}", info, e.fillInStackTrace());
            }
        }
    }

    /**
     * TopicExchange-one
     */
    public void sendMsgTopic(String msg, String routingKey) {
        if (!Strings.isNullOrEmpty(msg) && !Strings.isNullOrEmpty(routingKey)) {
            try {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.topic.exchange.name"));
                rabbitTemplate.setRoutingKey(routingKey);
                Message message=MessageBuilder.withBody(msg.getBytes("utf-8")).build();
                rabbitTemplate.convertAndSend(message);

                log.info("消息模型TopicExchange-生产者-发送消息：{}  路由:{}",msg,routingKey);
            } catch (Exception e) {
                e.printStackTrace();
                log.info("消息模型TopicExchange-生产者发生异常-发送消息：{}  路由:{}",msg,routingKey,e.fillInStackTrace());
            }
        }
    }
}