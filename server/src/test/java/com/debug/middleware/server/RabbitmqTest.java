package com.debug.middleware.server;

import com.debug.middleware.server.rabbitmq.consumer.BasicConsumer;
import com.debug.middleware.server.rabbitmq.entity.EventInfo;
import com.debug.middleware.server.rabbitmq.entity.Person;
import com.debug.middleware.server.rabbitmq.publisher.BasicPublisher;
import com.debug.middleware.server.rabbitmq.publisher.ModelPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jws.WebParam;

/**
 * @Author: AlexHsiao
 * @Date: 2020/11/20 上午10:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RabbitmqTest {
    private static final Logger log = LoggerFactory.getLogger(RabbitmqTest.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BasicPublisher basicPublisher;

    @Autowired
    private ModelPublisher modelPublisher;

    @Test
    public void test1(){
        String msg="这是一条字符串";
        basicPublisher.sendMsg(msg);
    }

    @Test
    public void test2(){
        Person person=new Person(1,"das","debug");
        basicPublisher.sendObjectMsg(person);
    }

    @Test
    public void test3(){
        EventInfo info=new EventInfo(1,"增删改查模块","基于fanoutExchange的消息模型","这是基于fanoutExchange的消息模型");
        modelPublisher.sendMsg(info);
    }
}
