package com.example.springbootdemo2.entity;

import java.io.Serializable;

public class Behaviour implements Serializable {
    //
    private Integer behaviourNo;
    private String nodeNo;
    private String timeStamp;
    private String packetNo;
    private String version;
    private String headerLength;
    private String typeOfService;
    private String totalLength;
    private String identifier;
    private String flags;
    private String fragmentOffset;
    private String ttl;
    private String protocol;
    private String headerChecksum;
    private String sourceAddresses;
    private String destinationAddresses;
    private String options;
    private String payload;

    private static final long serialVersionUID = 1995772553;

    public Behaviour() {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(String headerLength) {
        this.headerLength = headerLength;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public String getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(String totalLength) {
        this.totalLength = totalLength;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
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

    public String getHeaderChecksum() {
        return headerChecksum;
    }

    public void setHeaderChecksum(String headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

    public String getSourceAddresses() {
        return sourceAddresses;
    }

    public void setSourceAddresses(String sourceAddresses) {
        this.sourceAddresses = sourceAddresses;
    }

    public String getDestinationAddresses() {
        return destinationAddresses;
    }

    public void setDestinationAddresses(String destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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

    public Behaviour(Integer behaviourNo, String nodeNo, String timeStamp, String packetNo, String version, String headerLength, String typeOfService, String totalLength, String identifier, String flags, String fragmentOffset, String ttl, String protocol, String headerChecksum, String sourceAddresses, String destinationAddresses, String options, String payload) {
        this.behaviourNo = behaviourNo;
        this.nodeNo = nodeNo;
        this.timeStamp = timeStamp;
        this.packetNo = packetNo;
        this.version = version;
        this.headerLength = headerLength;
        this.typeOfService = typeOfService;
        this.totalLength = totalLength;
        this.identifier = identifier;
        this.flags = flags;
        this.fragmentOffset = fragmentOffset;
        this.ttl = ttl;
        this.protocol = protocol;
        this.headerChecksum = headerChecksum;
        this.sourceAddresses = sourceAddresses;
        this.destinationAddresses = destinationAddresses;
        this.options = options;
        this.payload = payload;
    }
}