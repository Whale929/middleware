package com.debug.middleware.model.mapper;


import com.debug.middleware.model.entity.RedRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface RedRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RedRecord record);

    @Insert("insert into red_record(id,user_id,red_packet,total,amount,is_active,create_time) value" +
            "(#{id},#{userId},#{redPacket},#{total},#{amount},#{isActive},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSelective(RedRecord redRecord);

    RedRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedRecord record);

    int updateByPrimaryKey(RedRecord record);
}