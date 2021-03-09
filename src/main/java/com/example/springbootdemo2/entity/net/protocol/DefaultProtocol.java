package com.example.springbootdemo2.entity.net.protocol;

import com.example.springbootdemo2.entity.net.baseLayer.NetLayer;

/**
 * @Author: AlexHsiao
 * @Date: 2021/3/8 下午3:37
 */
public class DefaultProtocol extends NetLayer {

    public DefaultProtocol() {
        this.buffer="default protocol";
        this.type="DEFAULT";
    }

    @Override
    public void parseBuffer() throws Exception{
        //throw new Exception("DefaultProtocol can't be parsed");
    }
}
