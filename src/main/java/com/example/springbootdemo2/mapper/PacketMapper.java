package com.example.springbootdemo2.mapper;

import com.example.springbootdemo2.entity.Packet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacketMapper {
    public void insertPacket(Packet packet);

    public Packet popPackt();

    public Packet topPacket();

    public List getPacketBySize();

    public List popPacketBySize();

    public int clearBySize();
}
