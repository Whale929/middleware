package com.example.springbootdemo2.controller;
import com.alibaba.fastjson.JSON;
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

@RestController
@RequestMapping("/behaviour")
public class BehaviourController {
    @Autowired
    private BehaviourService behaviourService;

    private static final Logger log= LoggerFactory.getLogger(BookController.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void one(Behaviour behaviour) throws JsonProcessingException {
        /*List<Behaviour> list=new ArrayList<>();
        list.add(behaviour);

        log.info("构造已经排好序的用户对象列表:{}",list);
        final String key="redis:test:2";
        ListOperations listOperations=redisTemplate.opsForList();
        log.info("写入redis:{}",objectMapper.writeValueAsString(behaviour));
        for(Behaviour p:list){
            listOperations.leftPush(key,p);
        }
        log.info("--获取Redis中List的数据-从队头中取出--");
        *//*Object result=listOperations.rightPop(key);
        Behaviour resP;
        while(result!=null){
            resP=(Behaviour)result;
            log.info("--当前数据--:{}",resP);
            result=listOperations.rightPop(key);
        }*//*
        new PersistendHandler(redisTemplate).run();*/
    }

    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ResultJson addBehaviour(int behaviourNo, String nodeNo, String timeStamp, String version,String headerLength,String typeOfService,
                                   String totalLength,String identifier,String flags,String fragmentOffset,String ttl,String protocol,
                                   String headerChecksum,String sourceAddresses,String destinationAddresses,String options,String payload) throws JsonProcessingException {
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

    @RequestMapping(value="getPacketByNodeNoPacketNo",method = RequestMethod.GET)
    public ResultJson getPacketByNodeNoPacketNo(String nodeNo,String packetNo){
        Ipv4Packet ipv4Packet=behaviourService.getPacketByNodeNoPacketNo(nodeNo,packetNo);
        ResultJson resultJson=new ResultJson(200,"OK",ipv4Packet);
        return resultJson;
    }

    @RequestMapping(value = "getPacketsByNodeNo", method = RequestMethod.GET)
    public ResultJson getPacketsByNodeId(String nodeNo) throws JsonProcessingException {

        ResultJson resultJson = new ResultJson();
        List<Ipv4Packet> ipv4PacketList = behaviourService.getPacketByNodeNo(nodeNo);
        resultJson = new ResultJson(200, "OK", ipv4PacketList);
        return resultJson;
    }

    /*    @RequestMapping(value = "getPacketsByNodeNo",method = RequestMethod.GET)
        public ResultJson getPacketsByNodeId(String nodeNo) throws JsonProcessingException {
            ResultJson resultJson=new ResultJson();
            List<Ipv4Packet> ipv4PacketList=behaviourService.getPacketByNodeNo(nodeNo);
            String resultMsg="[";
            for(Ipv4Packet b : ipv4PacketList)
            {
                resultMsg=resultMsg+b.toString()+",";
            }
            resultMsg.substring(resultMsg.length()-1);
            resultJson=new ResultJson("200","OK",resultMsg+"]");

            return  resultJson;
        }*/
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
