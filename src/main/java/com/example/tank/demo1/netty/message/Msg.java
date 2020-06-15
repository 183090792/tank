package com.example.tank.demo1.netty.message;

import java.io.IOException;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/6/5 11:31
 */
public interface Msg {
    public abstract void handle();
    public abstract byte[] toBytes() throws IOException;
    public abstract void parse(byte[] bytes);
    public abstract MsgType getMsgType();
}
