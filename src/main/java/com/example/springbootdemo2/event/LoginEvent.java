package com.example.springbootdemo2.event;


import org.springframework.context.ApplicationEvent;

import java.io.Serializable;



public class LoginEvent extends ApplicationEvent implements Serializable{

    private String userName; //用户名
    private String loginTime; //登录时间
    private String ip; //所在ip

    public LoginEvent(Object source) {
        super(source);
    }

    public LoginEvent(Object source, String userName, String loginTime, String ip) {
        super(source);
        this.userName = userName;
        this.loginTime = loginTime;
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LoginEvent{" +
                "userName='" + userName + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}