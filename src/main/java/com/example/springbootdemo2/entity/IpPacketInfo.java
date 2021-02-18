package com.example.springbootdemo2.entity;

import java.io.Serializable;

public class IpPacketInfo  implements Serializable {
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
