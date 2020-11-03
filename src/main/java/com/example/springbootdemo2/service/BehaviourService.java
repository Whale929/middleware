package com.example.springbootdemo2.service;

import com.example.springbootdemo2.controller.BookController;
import com.example.springbootdemo2.dao.BehaviourDao;
import com.example.springbootdemo2.entity.Behaviour;
import com.example.springbootdemo2.entity.Book;
import com.example.springbootdemo2.entity.Ipv4Packet;
import com.example.springbootdemo2.handler.PersistendHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BehaviourService {
    private static final Logger log= LoggerFactory.getLogger(BookController.class);

    @Resource
    private BehaviourDao behaviourDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void one(Behaviour behaviour) throws JsonProcessingException {
        List<Behaviour> list=new ArrayList<>();
        list.add(behaviour);

        log.info("构造已经排好序的用户对象列表:{}",list);
        final String key="redis:test:2";
        ListOperations listOperations=redisTemplate.opsForList();
        log.info("写入redis:{}",objectMapper.writeValueAsString(behaviour));
        for(Behaviour p:list){
            listOperations.leftPush(key,p);
        }
        log.info("--获取Redis中List的数据-从队头中取出--");

        //new PersistendHandler(redisTemplate).run();
    }

    public List<Behaviour> getBehaviourByNodeNo(String nodeNo) {
        List<Behaviour> behaviourList=new ArrayList<>();

        Behaviour behaviour;
        System.out.println(nodeNo);
        System.out.println(behaviourDao.getBehaviourByNodeNo(nodeNo));
        behaviourList=behaviourDao.getBehaviourByNodeNo(nodeNo);
        //System.out.println(behaviour.toString());
        return behaviourList;
    }

    public Ipv4Packet getPacketByNodeNoPacketNo(String nodeNo,String packetNo){
        Ipv4Packet ipv4Packet=behaviourDao.getPacketByNodeNoPacketNo(nodeNo,packetNo);
        return ipv4Packet;
    }

    public List<Ipv4Packet> getPacketByNodeNo(String nodeNo) {
        List<Ipv4Packet> packetList=new ArrayList<>();

        Ipv4Packet ipv4Packet;
        System.out.println(nodeNo);
        System.out.println(behaviourDao.getPacketByNodeNo(nodeNo));
        packetList=behaviourDao.getPacketByNodeNo(nodeNo);
        //System.out.println(behaviour.toString());
        return packetList;
    }

    public Boolean del() {
        final String key = "redis:test:2";
        return redisTemplate.delete(key);
    }
}
