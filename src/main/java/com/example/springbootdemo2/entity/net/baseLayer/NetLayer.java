package com.example.springbootdemo2.entity.net.baseLayer;

import java.io.Serializable;

/**
 * @Author: AlexHsiao
 * @Date: 2021/3/8 上午11:16
 */
public abstract class NetLayer implements Serializable {
    //协议类型
    protected String type;
    //字符串Json表示内容
    protected String buffer;
    //是否已将Json解析成具体成员对象
    protected boolean isParsed;

    /**
     * 将buffer中的字符串解析成具体成员变量
     */
    abstract public void parseBuffer() throws Exception;

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public boolean isParsed() {
        return isParsed;
    }

    public void setParsed(boolean parsed) {
        isParsed = parsed;
    }
}
