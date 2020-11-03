package com.example.springbootdemo2.controller;

import com.example.springbootdemo2.dao.BehaviourDao;
import com.example.springbootdemo2.handler.PersistendHandler;
import com.example.springbootdemo2.mapper.BehaviourMapper;
import com.example.springbootdemo2.service.BehaviourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/persistent")
public class PersistentController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private BehaviourDao behaviourDao;

    private static final Logger log= LoggerFactory.getLogger(BookController.class);

    @RequestMapping(value = "open",method = RequestMethod.GET)
    public void startWrite(){
        new PersistendHandler(redisTemplate,behaviourDao).run();
    }
}
