package com.example.springbootdemo2;

import com.example.springbootdemo2.rabbitmq.entity.Person;
import com.example.springbootdemo2.rabbitmq.publisher.BasicPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RabbitmqTest {
    private static final Logger log= LoggerFactory.getLogger(RabbitmqTest.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BasicPublisher basicPublisher;

    @Test
    public void test1(){
        String msg="{\"string\":\"这是字符串\"}";
        basicPublisher.sendMsg(msg);
    }

    @Test
    public void test2() throws Exception{
        Person p=new Person(1,"大圣","debug");
        basicPublisher.sendObjectMsg(p);
    }
}
