package com.debug.middleware.server.rabbitmq.publisher;

import com.debug.middleware.server.dto.UserLoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @Author: AlexHsiao
 * @Date: 2021/1/14 上午11:09
 */
public class LogPublisher {
    private static final Logger log= LoggerFactory.getLogger(LogPublisher.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    public void sendLogMsg(UserLoginDto loginDto){
        try {
            rabbitTemplate.setMessageConverter(new JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("mq.login.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("mq.login.routing.key.name"));

            rabbitTemplate.convertAndSend(loginDto, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties messageProperties=message.getMessageProperties();
                    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,UserLoginDto.class);
                    return message;
                }
            });
            log.info("系统日志记录-生产者-发送登陆成功后的用户相关信息入队列-内容:{}",loginDto);
        }catch (Exception e){
            log.error("系统日志记录-生产者-发送登陆成功后的用户相关信息入队列-发生异常",loginDto,e.fillInStackTrace());
        }
    }
}
