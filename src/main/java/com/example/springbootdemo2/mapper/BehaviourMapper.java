package com.example.springbootdemo2.mapper;

import com.example.springbootdemo2.entity.Behaviour;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BehaviourMapper{
    int insert(Behaviour behaviour);

    Behaviour selectByNo(@Param("behaviourNo") String behaviourNo);
}
