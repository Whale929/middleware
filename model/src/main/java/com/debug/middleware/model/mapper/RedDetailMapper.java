package com.debug.middleware.model.mapper;


import com.debug.middleware.model.entity.RedDetail;
import org.apache.ibatis.annotations.Insert;

public interface RedDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RedDetail record);

    @Insert("insert into red_detail(id,record_id,amount,is_active,create_time) values " +
            "(#{id},#{recordId},#{amount},#{isActive},#{createTime})")
    int insertSelective(RedDetail record);

    RedDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedDetail record);

    int updateByPrimaryKey(RedDetail record);
}