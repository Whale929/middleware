package com.example.springbootdemo2.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.springbootdemo2.entity.Behaviour;
import com.example.springbootdemo2.entity.Ipv4Packet;
import com.example.springbootdemo2.entity.ResultJson;
import com.example.springbootdemo2.service.BehaviourService;
import com.example.springbootdemo2.handler.PersistendHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/behaviour")
public class BehaviourController {
    @Autowired
    private BehaviourService behaviourService;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ResultJson addBehaviour(int behaviourNo, String nodeNo, String timeStamp, String version, String headerLength, String typeOfService,
                                   String totalLength, String identifier, String flags, String fragmentOffset, String ttl, String protocol,
                                   String headerChecksum, String sourceAddresses, String destinationAddresses, String options, String payload) throws JsonProcessingException {
        Behaviour behaviour = new Behaviour();
        behaviour.setBehaviourNo(behaviourNo);
        behaviour.setNodeNo(nodeNo);
        behaviour.setTimeStamp(timeStamp);
        behaviour.setVersion(version);
        behaviour.setHeaderLength(headerLength);
        behaviour.setTypeOfService(typeOfService);
        behaviour.setTotalLength(totalLength);
        behaviour.setIdentifier(identifier);
        behaviour.setFlags(flags);
        behaviour.setFragmentOffset(fragmentOffset);
        behaviour.setTtl(ttl);
        behaviour.setProtocol(protocol);
        behaviour.setHeaderChecksum(headerChecksum);
        behaviour.setSourceAddresses(sourceAddresses);
        behaviour.setDestinationAddresses(destinationAddresses);
        behaviour.setOptions(options);
        behaviour.setPayload(payload);
        behaviourService.one(behaviour);
        ResultJson resultJson = new ResultJson(200, "OK", behaviour);
        return resultJson;
    }

    @RequestMapping(value = "addTestData", method = RequestMethod.GET)
    public ResultJson addtestdata() throws JsonProcessingException {
        ResultJson resultJson=null;
        for(int i=0;i<10;i++) {
            int a = (int) (1 + Math.random() * 10);
            int b = (int) (1 + Math.random() * 10);
            String ip1 = "192.168.0." + String.valueOf(a);
            String ip2 = "192.168.0." + String.valueOf(b);
            Behaviour behaviour = new Behaviour();
            behaviour.setPacketNo(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setBehaviourNo((int) (1 + Math.random() * 10));
            behaviour.setNodeNo(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setTimeStamp(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setVersion(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setHeaderLength(String.valueOf((int) (1 + Math.random() * 100)));
            behaviour.setTypeOfService(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setTotalLength(String.valueOf((int) (1 + Math.random() * 1000)));
            behaviour.setIdentifier(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setFlags(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setFragmentOffset(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setTtl(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setProtocol(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setHeaderChecksum(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setSourceAddresses(ip1);
            behaviour.setDestinationAddresses(ip2);
            behaviour.setOptions(String.valueOf((int) (1 + Math.random() * 10)));
            behaviour.setPayload(String.valueOf((int) (1 + Math.random() * 10)));
            behaviourService.one(behaviour);
            resultJson = new ResultJson(200, "OK", behaviour);
        }
        return resultJson;
    }

    @RequestMapping(value = "getNextPacket", method = RequestMethod.GET)
    public ResultJson getNextPacket() {
        Behaviour behaviour = behaviourService.getNextPacket();
        ResultJson resultJson = new ResultJson(200, "OK", behaviour);

        return resultJson;
    }

    @RequestMapping(value = "getPacketsBySize", method = RequestMethod.GET)
    public ResultJson getPacketsBySize(int size) {
        ArrayList<Behaviour> list = new ArrayList<Behaviour>();
        ResultJson resultJson;

        if (size <= 0)
            return new ResultJson(400, "Parameter error:size must bigger than 1", list);
        list = behaviourService.getPacketsBySize(size);
        if (list.size() > 1)
            resultJson = new ResultJson(200, "OK", list);
        else
            resultJson = new ResultJson(300, "No data in Message Queue", list);
        return resultJson;
    }

    @RequestMapping(value = "getPacketByNodeNoPacketNo", method = RequestMethod.GET)
    public ResultJson getPacketByNodeNoPacketNo(String nodeNo, String packetNo) {
        Ipv4Packet ipv4Packet = behaviourService.getPacketByNodeNoPacketNo(nodeNo, packetNo);
        ResultJson resultJson = new ResultJson(200, "OK", ipv4Packet);
        return resultJson;
    }

    @RequestMapping(value = "getPacketsByNodeNo", method = RequestMethod.GET)
    public ResultJson getPacketsByNodeId(String nodeNo) throws JsonProcessingException {

        ResultJson resultJson = new ResultJson();
        List<Ipv4Packet> ipv4PacketList = behaviourService.getPacketByNodeNo(nodeNo);
        resultJson = new ResultJson(200, "OK", ipv4PacketList);
        return resultJson;
    }

    @RequestMapping("test")
    public ResultJson test(String p1, String p2) {
        ResultJson resultJson = new ResultJson(200, "OK", "null");
        return resultJson;
    }

    @RequestMapping(value = "del", method = RequestMethod.GET)
    public ResultJson deleteAllBehaviour() {
        if (behaviourService.del()) {
            return new ResultJson(200, "ok", null);
        } else
            return new ResultJson(505, "error", "delete keys error in redis");
    }
}
