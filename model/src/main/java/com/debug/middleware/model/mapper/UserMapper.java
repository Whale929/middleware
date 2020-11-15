package com.debug.middleware.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:20
 */
@Repository
public interface UserMapper {
    @Insert("insert into user(userid,username) value ('123','ssscc')")
    void insertUser();
}

