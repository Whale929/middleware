package com.debug.middleware.server.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: AlexHsiao
 * @Date: 2020/11/21 下午3:55
 */
@Data
@ToString
public class EventInfo implements Serializable {

    private Integer id;
    private String module;
    private String name;
    private String desc;

    public EventInfo() {
    }

    public EventInfo(Integer id, String module, String name, String desc) {
        this.id = id;
        this.module = module;
        this.name = name;
        this.desc = desc;
    }
}