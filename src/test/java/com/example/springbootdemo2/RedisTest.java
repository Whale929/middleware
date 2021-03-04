package com.example.springbootdemo2;

import com.example.springbootdemo2.entity.Packet;
import com.example.springbootdemo2.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    private static final Logger log= LoggerFactory.getLogger(RedisTest.class);
    @Autowired
    private RedisTemplate redisTemplate;

    final String key = "redis:packet:1";

    @Test
    public void one(){
        log.info("----开始----");
        final String content="RedisTemplate实战字符串信息";
        final String key="redis:template:string";
        ValueOperations valueOperations=redisTemplate.opsForValue();
        log.info("写入缓存中的内容：{} ",content);
        valueOperations.set(key,content);
        Object result=valueOperations.get(key);
        log.info("读取出来的内容：{}",result);
    }
    @Autowired
    private ObjectMapper objectMapper;

    //采用RedisTemplate将一对象信息序列化为Json格式字符串后写入缓存中，
    //然后将其读取出来，最后反序列化解析其中的内容并展示在控制台
    @Test
    public void two() throws Exception{
        log.info("------开始RedisTemplate操作组件实战----");

        //构造对象信息
        User user=new User(1,"debug","阿修罗");

        //Redis通用的操作组件
        ValueOperations valueOperations=redisTemplate.opsForValue();

        //将序列化后的信息写入缓存中
        final String key="redis:template:two:object";
        //final String content=objectMapper.writeValueAsString(user);
        final String content=user.toString();

        valueOperations.set(key,content);
        log.info("写入缓存对象的信息：{} ",user);

        //从缓存中读取内容
/*        Object result=valueOperations.get(key);
        if (result!=null){
            User resultUser=objectMapper.readValue(result.toString(), User.class);
            log.info("读取缓存内容并反序列化后的结果：{} ",resultUser.toString());
        }*/
    }

    @Test
    public void three(){
        Packet packet = null;
        Object obj = redisTemplate.opsForList().index(key,redisTemplate.opsForList().size(key)-1);
        if (obj != null) {
            packet = (Packet) obj;
            System.out.println("obj:" + obj.toString());
            System.out.println(packet.toString());
        }
    }
}
