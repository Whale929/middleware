package com.debug.middleware.server;

import com.debug.middleware.model.mapper.UserMapper;
import com.debug.middleware.server.event.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EventTest {

    @Autowired
    private Publisher publisher;

    @Test
    public void test1() throws Exception {
        publisher.sendMsg();
    }
}
