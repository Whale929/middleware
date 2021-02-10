package com.example.springbootdemo2.handler;


import com.example.springbootdemo2.controller.PersistentController;
import com.example.springbootdemo2.dao.BehaviourDao;
import com.example.springbootdemo2.entity.Behaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;


public class PersistendHandler extends Thread {

    public PersistendHandler(RedisTemplate redisTemplate, BehaviourDao behaviourDao){
        this.redisTemplate=redisTemplate;
        this.behaviourDao=behaviourDao;
    }

    private static final Logger log= LoggerFactory.getLogger(PersistendHandler.class);

    private RedisTemplate redisTemplate;

    private BehaviourDao behaviourDao;

    public void run() {
        //this.redisTemplate= BeanContext.getApplicationContext().getBean(RedisTemplate.class);
        while(true) {
            ListOperations listOperations = redisTemplate.opsForList();
            final String key = "redis:test:2";
            Object result = listOperations.rightPop(key);
            Behaviour resP;
            resP = (Behaviour) result;
            log.info("--当前数据多线程读取--:{}", resP);
        /*while(result!=null){
            resP=(Behaviour)result;
            log.info("--当前数据多线程读取--:{}",resP);
            result=listOperations.rightPop(key);
        }*/
            if(resP!=null)
                behaviourDao.addBehaviour(resP);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
