package com.example.springbootdemo2.entity;

import java.io.Serializable;

public class Behaviour implements Serializable {
    //
    private Integer behaviourNo;
    private String nodeNo;
    private String timeStamp;
    private String packetNo;
    private Ipv4Packet ipv4Packet;
    private String payload;

    private static final long serialVersionUID = 1995772553;

    public Behaviour() {
    }

    public Behaviour(Integer behaviourNo, String nodeNo, String timeStamp, String packetNo, Ipv4Packet ipv4Packet, String payload) {
        this.behaviourNo = behaviourNo;
        this.nodeNo = nodeNo;
        this.timeStamp = timeStamp;
        this.packetNo = packetNo;
        this.ipv4Packet = ipv4Packet;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Behaviour{" +
                "behaviourNo=" + behaviourNo +
                ", nodeNo='" + nodeNo + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", packetNo='" + packetNo + '\'' +
                ", ipv4Packet=" + ipv4Packet +
                ", payload='" + payload + '\'' +
                '}';
    }

    public Integer getBehaviourNo() {
        return behaviourNo;
    }

    public void setBehaviourNo(Integer behaviourNo) {
        this.behaviourNo = behaviourNo;
    }

    public String getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(String nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPacketNo() {
        return packetNo;
    }

    public void setPacketNo(String packetNo) {
        this.packetNo = packetNo;
    }

    public Ipv4Packet getIpv4Packet() {
        return ipv4Packet;
    }

    public void setIpv4Packet(Ipv4Packet ipv4Packet) {
        this.ipv4Packet = ipv4Packet;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}