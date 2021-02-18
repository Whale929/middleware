package com.example.springbootdemo2.entity;

import java.io.Serializable;

public class TcpPacketInfo  implements Serializable {
    private String sourcePort;
    private String destinationPort;
    private String sequenceNumber;
    private String ackNumber;
    private String flags;
    private String windowSize;

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
