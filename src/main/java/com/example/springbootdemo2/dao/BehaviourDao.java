package com.example.springbootdemo2.dao;

import com.example.springbootdemo2.entity.Behaviour;
import com.example.springbootdemo2.entity.Ipv4Packet;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BehaviourDao {
    @Insert("insert into behaviourtable(nodeNo,timeStamp,version ,headerLength ,typeOfService ,totalLength ," +
            "identifier ,flags ,fragmentOffset ,ttl ,protocol ,headerChecksum ,sourceAddresses ,destinationAddresses ,options ,payload ) " +
            "value(#{nodeNo},#{timeStamp},#{version},#{headerLength},#{typeOfService},#{totalLength},#{identifier}," +
            "#{flags},#{fragmentOffset},#{ttl},#{ttl},#{protocol},#{headerChecksum}," +
            "#{sourceAddresses},#{options},#{payload})")
    void addBehaviour(Behaviour behaviour);


    @Insert("INSERT INTO behaviourtable(nodeNo,timeStamp,version) value (#{nodeNo},#{timeStamp},#{ipv4Packet.version})")
    void addBehaviour2(Behaviour behaviour);

    @Select("SELECT * FROM behaviourtable WHERE nodeNo = #{nodeNo}")
    List<Behaviour> getBehaviourByNodeNo(String nodeNo);

    @Select("SELECT version ,headerLength ,typeOfService ,totalLength ,identifier ,flags ,fragmentOffset ,ttl ,protocol ,headerChecksum ,sourceAddresses ,destinationAddresses ,options " +
            " FROM behaviourtable WHERE nodeNo = #{nodeNo}")
    List<Ipv4Packet> getPacketByNodeNo(String nodeNo);

    @Select("SELECT version ,headerLength ,typeOfService ,totalLength ,identifier ,flags ,fragmentOffset ,ttl ,protocol ,headerChecksum ,sourceAddresses ,destinationAddresses ,options " +
            " FROM behaviourtable WHERE nodeNo = #{nodeNo} AND packetNo = #{packetNo}")
    Ipv4Packet getPacketByNodeNoPacketNo(String nodeNo, String packetNo);
}
