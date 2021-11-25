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
    public void test1() {
        String msg = "这是一条字符串";
        basicPublisher.sendMsg(msg);
    }

    @Test
    public void test2() {
        Person person = new Person(1, "das", "debug");
        basicPublisher.sendObjectMsg(person);
    }

    @Test
    public void test3() {
        EventInfo info = new EventInfo(1, "增删改查模块", "基于fanoutExchange的消息模型", "这是基于fanoutExchange的消息模型");
        modelPublisher.sendMsgFanout(info);
    }

    @Test
    public void test4() {
        EventInfo info = new EventInfo(1, "增删改查模块-1", "基于directExchange消息模型-1", "directExchange-1");
        modelPublisher.sendMsgDirectOne(info);

        info = new EventInfo(2, "增删改查模块-2", "基于directExchange消息模型-2", "directExchange-2");
        modelPublisher.sendMsgDirectTwo(info);
    }

    @Test
    public void test5(){
        String msg="这是TopicExchange消息模型的消息";

        String routingKeyOne="local.middleware.mq.topic.routing.java.key";
        String routingKeyTwo="local.middleware.mq.topic.routing.php.python.key";
        String routingKeyThree="local.middleware.mq.topic.routing.key";

        //modelPublisher.sendMsgTopic(msg,routingKeyOne);
        //modelPublisher.sendMsgTopic(msg,routingKeyTwo);
        modelPublisher.sendMsgTopic(msg,routingKeyThree);
    }
}
