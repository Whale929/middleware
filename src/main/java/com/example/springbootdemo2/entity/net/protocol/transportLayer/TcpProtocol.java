package com.example.springbootdemo2.entity.net.protocol.transportLayer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.example.springbootdemo2.entity.net.baseLayer.NetworkLayer;
import com.example.springbootdemo2.entity.net.baseLayer.TransportLayer;

import java.io.Serializable;

/**
 * @Author: AlexHsiao
 * @Date: 2021/3/8 下午5:06
 */
public class TcpProtocol extends TransportLayer {
    @JSONField(serialize = false)
    private long TcpProtocolId;

    //具体协议内容
    private String sourcePort;
    private String destinationPort;
    private String sequenceNumber;
    private String ackNumber;
    private String flags;
    private String windowSize;

    /**
     * 将buffer中的字符串解析成具体成员变量
     */
    @Override
    public void parseBuffer() {
        final String jsonData=this.buffer;
        if(!(jsonData.isEmpty()||isParsed)){
            JSONObject jsonObject= JSON.parseObject(buffer);
            if(jsonObject.get("protocol").equals("tcp")){
                String content=jsonObject.getString("content");
                JSONObject json=JSON.parseObject(content);

                this.parseSet(json.getString("sourcePort"),json.getString("destinationPort"),json.getString("sequenceNumber"),
                        json.getString("ackNumber"), json.getString("flags"),json.getString("windowSize"));
            }
        }
    }

    public void parseSet(String sourcePort, String destinationPort, String sequenceNumber, String ackNumber, String flags, String windowSize) {
        this.sourcePort = sourcePort;
        this.destinationPort = destinationPort;
        this.sequenceNumber = sequenceNumber;
        this.ackNumber = ackNumber;
        this.flags = flags;
        this.windowSize = windowSize;
    }

    public TcpProtocol(String buffer) {
        this.buffer = buffer;
    }

    public long getTcpProtocolId() {
        return TcpProtocolId;
    }

    public void setTcpProtocolId(long tcpProtocolId) {
        TcpProtocolId = tcpProtocolId;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getAckNumber() {
        return ackNumber;
    }

    public void setAckNumber(String ackNumber) {
        this.ackNumber = ackNumber;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(String windowSize) {
        this.windowSize = windowSize;
    }
}
