package com.debug.middleware.server.service;

import com.debug.middleware.model.entity.User;
import com.debug.middleware.model.mapper.UserMapper;
import com.debug.middleware.server.dto.UserLoginDto;
import com.debug.middleware.server.rabbitmq.publisher.LogPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: AlexHsiao
 * @Date: 2021/1/14 上午10:30
 */

@Service
public class UserService {
    private static final Logger log= LoggerFactory.getLogger(UserService.class);

    //@Autowired
    //private UserMapper userMapper;


    private LogPublisher logPublisher=new LogPublisher();

    public Boolean login(UserLoginDto dto){
        User user;

        logPublisher.sendLogMsg(dto);
        return true;
    }
}
