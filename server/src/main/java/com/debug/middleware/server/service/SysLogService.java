package com.debug.middleware.server.service;

import com.debug.middleware.model.entity.SysLog;
import com.debug.middleware.server.dto.UserLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;

/**
 * @Author: AlexHsiao
 * @Date: 2021/1/14 下午5:03
 */
public class SysLogService {
    private static final Logger log = LoggerFactory.getLogger(SysLogService.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 记录用户登陆成功的信息入数据库
     */
    @Async
    public void recordLog(UserLoginDto dto) {
        try {
            SysLog entity = new SysLog();
            entity.setUserId(dto.getUserId());
            entity.setModule("用户登录模块");
            entity.setData(objectMapper.writeValueAsString(dto));
            entity.setMemo("用户登录成功记录相关登录信息");
            entity.setCreateTime(new Date());

            log.info("插入数据库：{}", entity.toString());
        } catch (Exception e) {
            log.error("系统日志服务-记录用户登录成功的信息入数据库-发生异常：{} ", dto, e.fillInStackTrace());
        }
    }
}
