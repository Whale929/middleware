package com.example.springbootdemo2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestKeys {

    //客户端对象
    Jedis jedis = null;
    JedisPool jedisPool = null;

    @Test
    public void TestKeys(){
        System.out.println("清空数据库："+jedis.flushDB());
    }

    @Before
    public void init(){
        //设置连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置连接池参数
        config.setMaxTotal(30);
        config.setMaxIdle(10);
        //获取连接池对象
        jedisPool = new JedisPool(config, "192.168.40.133", 6379);
        try{
            jedis = jedisPool.getResource();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @After
    public void closeResource()
    {
        if(jedis !=null)
            jedis.close();
    }
}