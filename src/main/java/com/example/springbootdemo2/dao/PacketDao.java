package com.example.springbootdemo2.dao;

import com.example.springbootdemo2.entity.Packet;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PacketDao {

    public void insertPacket(Packet packet);

    public Packet popPackt();

    public Packet topPacket();

    public List getPacketBySize();

    public List popPacketBySize();

    public int clearBySize();
}
