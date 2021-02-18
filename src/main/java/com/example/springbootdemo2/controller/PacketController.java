package com.example.springbootdemo2.controller;

import com.example.springbootdemo2.entity.*;
import com.example.springbootdemo2.service.BehaviourService;
import com.example.springbootdemo2.service.PacketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/packet")
public class PacketController {
    @Autowired
    private BehaviourService behaviourService;

    @Autowired
    private PacketService packetService;

    private static Logger log = LoggerFactory.getLogger(PacketController.class);


    @GetMapping("getPacketsBySize")
    public ResultJson getPacketsBySize(Integer size) {
        ResultJson resultJson = new ResultJson();
        ArrayList<Packet> arrayList;
        //Get中未携带size参数。自动为size赋值1
        if (size == null)
            size = 1;

        //size小于0，报错
        if (size < 0) {
            resultJson.setMessage("parameter error:size must bigger than 0");
            resultJson.setStatus(400);
            return resultJson;
        } else {
            arrayList = packetService.getPacketBySize(size);
        }

        if (arrayList.size() == 0) {
            resultJson.setStatus(400);
            resultJson.setMessage("No data in server.");
        } else {
            resultJson.setStatus(200);
            resultJson.setMessage("ok");
            resultJson.setData(arrayList);
        }
        return resultJson;
    }

    @RequestMapping(value = "inputPacket", method = RequestMethod.POST)
    public ResultJson inputPacket(@RequestBody Packet packet) throws JsonProcessingException {

        packetService.addPacket(packet);
        ResultJson resultJson = new ResultJson(200, "OK", packet);
        return resultJson;
    }

    @GetMapping("addTestData")
    public ResultJson addTestData(Integer packetNum, Integer nodeNum) throws JsonProcessingException {
        if (packetNum <= 0 || nodeNum <= 0) {
             return new ResultJson(400,"error","parameter error");
        }

        ArrayList<Packet> arrayList = new ArrayList<>();
        Packet packet;
        for (int i = 0; i < packetNum; i++) {
            packet = createRandomPacket(nodeNum);
            arrayList.add(packet);
            //
            packetService.addPacket(packet);
            //
        }

        ResultJson resultJson = new ResultJson();
        resultJson.setStatus(200);
        resultJson.setMessage("ok");
        resultJson.setData(arrayList);

        return resultJson;
    }

    @PostMapping("person")
    public ResultJson getPerson(@RequestBody Person person) {
        ResultJson resultJson = new ResultJson();
        resultJson.setData(person);
        return resultJson;
    }

    private Packet createRandomPacket(Integer NodeNum) {
        String ipAddr = "192.168.0.";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        IpPacketInfo ipPacketInfo = new IpPacketInfo();
        ipPacketInfo.setTimestamp(date);
        ipPacketInfo.setIpPacketHeadLength((int) (Math.random() * 50) + "");
        ipPacketInfo.setIdentification((int) (Math.random() * 50) + "");
        ipPacketInfo.setTos((int) (Math.random() * 50) + "");
        ipPacketInfo.setIpPacketTotalLength((int) (Math.random() * 50) + "");
        ipPacketInfo.setFragmentOffset((int) (Math.random() * 50) + "");
        ipPacketInfo.setFragmentOffset((int) (Math.random() * 50) + "");
        ipPacketInfo.setTtl((int) (Math.random() * 50) + "");
        ipPacketInfo.setProtocol((int) (Math.random() * 50) + "");
        ipPacketInfo.setSourceAddress(ipAddr + (int) (Math.random() * NodeNum));
        ipPacketInfo.setDestinationAddress(ipAddr + (int) (Math.random() * NodeNum));

        TcpPacketInfo tcpPacketInfo = new TcpPacketInfo();
        tcpPacketInfo.setSourcePort((int) (Math.random() * 50) + "");
        tcpPacketInfo.setDestinationPort((int) (Math.random() * 50) + "");
        tcpPacketInfo.setSequenceNumber((int) (Math.random() * 50) + "");
        tcpPacketInfo.setAckNumber((int) (Math.random() * 50) + "");
        tcpPacketInfo.setAckNumber((int) (Math.random() * 50) + "");
        tcpPacketInfo.setFlags((int) (Math.random() * 50) + "");
        tcpPacketInfo.setWindowSize((int) (Math.random() * 500) + "");

        Payload payload = new Payload();
        payload.setPayloadSize((int) (Math.random() * 50) + "");
        payload.setPayload("payload");

        Packet packet = new Packet();
        packet.setBehaviourNo("test" + (int) (Math.random() * 100000));
        packet.setIpPacketInfo(ipPacketInfo);
        packet.setTcpPacketInfo(tcpPacketInfo);
        packet.setPayload(payload);

        return packet;
    }

}