package com.example.tank.demo1.netty;

import com.example.tank.demo1.netty.message.TankMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class TankMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 9) {
            return;
        }
        int x = byteBuf.readInt();
        int y = byteBuf.readInt();
        boolean living = byteBuf.readBoolean();
//        int oldX = byteBuf.readInt();
//        int oldY = byteBuf.readInt();
        list.add(new TankMsg(x,y,living));
//        list.add(new TankMsg(x,y,living,oldX,oldY));
    }
}
