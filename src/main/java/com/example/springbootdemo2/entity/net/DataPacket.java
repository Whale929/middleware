package com.example.springbootdemo2.entity.net;

import com.example.springbootdemo2.entity.net.baseLayer.*;
import com.example.springbootdemo2.entity.net.protocol.DefaultProtocol;

/**
 * @Author: AlexHsiao
 * @Date: 2021/3/8 下午2:34
 */
public class DataPacket {
    private long dataPacketId;

    //网络五层协议
    private NetLayer applicationLayer;
    private NetLayer transportLayer;
    private NetLayer networkLayer;
    private NetLayer dataLinkLayer;
    private NetLayer physicalLayer;

    public void parseJson() throws Exception {
        applicationLayer.parseBuffer();
        transportLayer.parseBuffer();
        networkLayer.parseBuffer();
        dataLinkLayer.parseBuffer();
        physicalLayer.parseBuffer();
    }

    public DataPacket() {
        this.applicationLayer = new DefaultProtocol();
        this.transportLayer = new DefaultProtocol();
        this.networkLayer = new DefaultProtocol();
        this.dataLinkLayer = new DefaultProtocol();
        this.physicalLayer = new DefaultProtocol();
    }

    public long getDataPacketId() {
        return dataPacketId;
    }

    public void setDataPacketId(long dataPacketId) {
        this.dataPacketId = dataPacketId;
    }

    public NetLayer getApplicationLayer() {
        return applicationLayer;
    }

    public void setApplicationLayer(NetLayer applicationLayer) {
        this.applicationLayer = applicationLayer;
    }

    public NetLayer getTransportLayer() {
        return transportLayer;
    }

    public void setTransportLayer(NetLayer transportLayer) {
        this.transportLayer = transportLayer;
    }

    public NetLayer getNetworkLayer() {
        return networkLayer;
    }

    public void setNetworkLayer(NetLayer networkLayer) {
        this.networkLayer = networkLayer;
    }

    public NetLayer getDataLinkLayer() {
        return dataLinkLayer;
    }

    public void setDataLinkLayer(NetLayer dataLinkLayer) {
        this.dataLinkLayer = dataLinkLayer;
    }

    public NetLayer getPhysicalLayer() {
        return physicalLayer;
    }

    public void setPhysicalLayer(NetLayer physicalLayer) {
        this.physicalLayer = physicalLayer;
    }
}
