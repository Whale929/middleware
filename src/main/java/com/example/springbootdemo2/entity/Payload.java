package com.example.springbootdemo2.entity;

import java.io.Serializable;

public class Payload  implements Serializable {
    private String payloadSize;
    private String payload;

    public void setPayloadSize(String payloadSize) {
        this.payloadSize = payloadSize;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getPayloadSize() {
        return payloadSize;
    }

    public String getPayload() {
        return payload;
    }
}
