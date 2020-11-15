package com.debug.middleware.model.mapper;


import com.debug.middleware.model.entity.RedRobRecord;
import org.apache.ibatis.annotations.Insert;

public interface RedRobRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RedRobRecord record);

    @Insert("insert into red_rob_record (user_id,red_packet,amount,rob_time,is_active) value" +
            "(#{userId},#{redPacket},#{amount},#{robTime},#{isActive})")
    int insertSelective(RedRobRecord record);

    RedRobRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedRobRecord record);

    int updateByPrimaryKey(RedRobRecord record);
}