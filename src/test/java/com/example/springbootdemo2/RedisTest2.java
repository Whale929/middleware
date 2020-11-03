package com.example.springbootdemo2;

import com.example.springbootdemo2.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest2 {
    private static final Logger logger= LoggerFactory.getLogger(RedisTest2.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void one() throws JsonProcessingException {
        Person p=new Person(10013,34,"xl","debug","hx");
        final String key="redis:test:1";
        String value=objectMapper.writeValueAsString(p);
        logger.info("存入缓存中的用户实体对象信息为：{}",p.toString());
        ValueOperations valueOperations=redisTemplate.opsForValue();
        valueOperations.set(key,value);
        Object result=valueOperations.get(key);
        if(result!=null){
            Person resP=objectMapper.readValue(result.toString(),Person.class);
            logger.info("从缓存中读取信息:{}",result.toString());
            logger.info("从缓存中读取信息:{}",resP.toString());
        }
    }

    @Test
    public void two(){
        List<Person> list=new ArrayList<>();
        list.add(new Person(1,21,"xl","debug","hx"));
        list.add(new Person(2,22,"xl2","debug2","hx2"));
        list.add(new Person(3,23,"xl3","debug3","hx3"));
        list.add(new Person(4,24,"xl4","debug4","hx4"));
        logger.info("构造已经排好序的用户对象列表:{}",list);
        final String key="redis:test:2";
        ListOperations listOperations=redisTemplate.opsForList();

        for(Person p:list){
            logger.info("写入redis的数据:{}",p);
            listOperations.leftPush(key,p);
        }
        logger.info("--获取Redis中List的数据-从队头中取出--");
        Object result=listOperations.rightPop(key);
        Person resP;
        while(result!=null){
            resP=(Person) result;
            logger.info("--当前数据--:{}",resP.toString());
            result=listOperations.rightPop(key);
        }
    }



}
