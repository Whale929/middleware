package com.example.springbootdemo2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootdemo2.entity.Fruit;
import com.example.springbootdemo2.entity.net.protocol.networkLayer.Ipv4Protocol;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * @Author: AlexHsiao
 * @Date: 2021/3/8 下午3:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JsonTest {
    @Test
    public void test1(){

        String json="{\n" +
                "    \"type\":\"fruit\",\n" +
                "    \"content\":{\n" +
                "        \"name\":\"apple\",\n" +
                "        \"color\":\"red\"\n" +
                "    }\n" +
                "}";

        JSONObject map;
        map=JSON.parseObject(json);
        System.out.println(map.get("type"));
        String f=map.getString("content");
        Fruit fruit=JSON.parseObject(f,Fruit.class);
        System.out.println(fruit.getName());
    }

    @Test
    public void test2(){

        String json="{\n" +
                "    \"protocol\":\"ipv4\",\n" +
                "    \"content\":{\n" +
                "        \"timestamp\":\"1\",\n" +
                "        \"ipPacketHeadLength\":\"2\",\n" +
                "        \"identification\":\"identification\"\n" +
                "    }\n" +
                "}";

        Ipv4Protocol ipv4Protocol=new Ipv4Protocol();
        ipv4Protocol.setBuffer(json);
        ipv4Protocol.parseBuffer();
        System.out.println(ipv4Protocol.getTimestamp());
    }
}
