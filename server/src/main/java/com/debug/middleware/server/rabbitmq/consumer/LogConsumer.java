package com.debug.middleware.server.rabbitmq.consumer;

import com.debug.middleware.server.dto.UserLoginDto;
import com.debug.middleware.server.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @Author: AlexHsiao
 * @Date: 2021/1/14 下午4:57
 */
public class LogConsumer {
    private static final Logger log = LoggerFactory.getLogger(LogConsumer.class);

    @Autowired
    private SysLogService sysLogService;

    /**
     * 监听消费并处理用户登陆成功后的消息
     */
    @RabbitListener(queues = "${mq.login.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeMsg(@Payload UserLoginDto loginDto) {
        try {
            log.info("系统日志记录-消费者-监听消费用户登录成功后的消息-内容：{}", loginDto);
            sysLogService.recordLog(loginDto);
        } catch (Exception e) {
            log.error("系统日志记录-消费者-监听消费用户登录成功后的消息-发生异常：{} ", loginDto, e.fillInStackTrace());
        }
    }

}
