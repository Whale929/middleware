package com.example.springbootdemo2.entity.net.protocol.networkLayer;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.springbootdemo2.entity.net.baseLayer.NetworkLayer;
import com.alibaba.fastjson.*;
import com.fasterxml.jackson.annotation.JsonFilter;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Author: AlexHsiao
 * @Date: 2021/3/8 下午2:39
 */
public class Ipv4Protocol extends NetworkLayer {
    @JSONField(serialize = false)
    private long ipv4ProtocolId;

    //具体协议内容
    private String timestamp;
    private String ipPacketHeadLength;
    private String identification;
    private String tos;
    private String ipPacketTotalLength;
    private String fragmentOffset;
    private String ttl;
    private String protocol;
    private String sourceAddress;
    private String destinationAddress;

    /**
     * 将buffer中的字符串解析成具体成员变量
     */
    @Override
    public void parseBuffer() {
        final String jsonData=this.buffer;
        if(!(jsonData.isEmpty()||isParsed)){
            JSONObject jsonObject=JSON.parseObject(buffer);
            if(jsonObject.get("protocol").equals("ipv4")){
                String content=jsonObject.getString("content");
                JSONObject json=JSON.parseObject(content);

                this.parseSet(json.getString("timestamp"),json.getString("ipPacketHeadLength"),json.getString("identification"),
                        json.getString("tos"),json.getString("ipPacketTotalLength"),json.getString("fragmentOffset"),
                        json.getString("ttl"), json.getString("protocol"),json.getString("sourceAddress"),json.getString("destinationAddress"));
            }
        }
    }

    private void parseSet(String timestamp, String ipPacketHeadLength, String identification, String tos, String ipPacketTotalLength, String fragmentOffset, String ttl, String protocol, String sourceAddress, String destinationAddress) {
        this.timestamp = timestamp;
        this.ipPacketHeadLength = ipPacketHeadLength;
        this.identification = identification;
        this.tos = tos;
        this.ipPacketTotalLength = ipPacketTotalLength;
        this.fragmentOffset = fragmentOffset;
        this.ttl = ttl;
        this.protocol = protocol;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
    }

    public Ipv4Protocol(String buffer) {
        this.buffer = buffer;
    }

    public long getIpv4ProtocolId() {
        return ipv4ProtocolId;
    }

    public void setIpv4ProtocolId(long ipv4ProtocolId) {
        this.ipv4ProtocolId = ipv4ProtocolId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getIpPacketHeadLength() {
        return ipPacketHeadLength;
    }

    public void setIpPacketHeadLength(String ipPacketHeadLength) {
        this.ipPacketHeadLength = ipPacketHeadLength;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getTos() {
        return tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }

    public String getIpPacketTotalLength() {
        return ipPacketTotalLength;
    }

    public void setIpPacketTotalLength(String ipPacketTotalLength) {
        this.ipPacketTotalLength = ipPacketTotalLength;
    }

    public String getFragmentOffset() {
        return fragmentOffset;
    }

    public void setFragmentOffset(String fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }
}
