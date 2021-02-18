package com.example.springbootdemo2.service;

import com.example.springbootdemo2.dao.BehaviourDao;
import com.example.springbootdemo2.entity.Behaviour;
import com.example.springbootdemo2.entity.Packet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacketService {
    private Logger log = LoggerFactory.getLogger(PacketService.class);

    final String key = "redis:packet:1";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void addPacket(Packet packet) throws JsonProcessingException {
        this.addToRedis(packet);
    }

    public Packet getNextPacket() {
        Packet packet = null;
        Object obj = redisTemplate.opsForList().rightPop(key);
        if (obj != null) {
            packet = (Packet) obj;
            System.out.println("obj:" + obj.toString());
            System.out.println(packet.toString());
        }
        return packet;
    }

    public ArrayList getPacketBySize(int size) {
        ArrayList<Packet> list = new ArrayList<>();

        while (size > 0) {
            Packet packet = getNextPacket();
            if(packet==null)
                break;
            size--;
            list.add(packet);
        }
        return list;
    }

    private void addToRedis(Packet packet) throws JsonProcessingException {
        List<Packet> list = new ArrayList<>();
        list.add(packet);

        log.info("构造已经排好序的用户对象列表:{}", list);

        ListOperations listOperations = redisTemplate.opsForList();
        log.info("写入redis:{}", objectMapper.writeValueAsString(packet));
        for (Packet p : list) {
            listOperations.leftPush(key, p);
        }
        //new PersistendHandler(redisTemplate).run();
    }
}
