package com.example.springbootdemo2.entity;

import java.io.Serializable;

public class Packet  implements Serializable {
    //id
    private String behaviourNo;

    //IP Packet Info
    private IpPacketInfo ipPacketInfo;

    //TCP Packet Info
    private TcpPacketInfo tcpPacketInfo;

    //payload
    private Payload payload;

    public String getBehaviourNo() {
        return behaviourNo;
    }

    public void setBehaviourNo(String behaviourNo) {
        this.behaviourNo = behaviourNo;
    }

    public IpPacketInfo getIpPacketInfo() {
        return ipPacketInfo;
    }

    public void setIpPacketInfo(IpPacketInfo ipPacketInfo) {
        this.ipPacketInfo = ipPacketInfo;
    }

    public TcpPacketInfo getTcpPacketInfo() {
        return tcpPacketInfo;
    }

    public void setTcpPacketInfo(TcpPacketInfo tcpPacketInfo) {
        this.tcpPacketInfo = tcpPacketInfo;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
