package com.example.springbootdemo2.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultJson {
    private String timestamp;
    private int status;
    private String message;
    private Object data;

    public ResultJson(){}

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public ResultJson(int status, String message, Object data) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timestamp = df.format(new Date());
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
