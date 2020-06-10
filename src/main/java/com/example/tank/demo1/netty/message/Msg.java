package com.example.tank.demo1.netty.message;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/6/5 11:31
 */
public interface Msg {
    public abstract void handle();
    public abstract byte[] toBytes();
    public abstract void parse(byte[] bytes);
    public abstract MsgType getMsgType();
}
