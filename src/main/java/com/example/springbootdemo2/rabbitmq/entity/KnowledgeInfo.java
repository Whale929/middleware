package com.example.springbootdemo2.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class KnowledgeInfo implements Serializable{

    private Integer id; //id标识
    private String mode;//模式名称
    private String code;//对应编码
}






























