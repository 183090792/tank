package com.example.tank.demo1.netty;

import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Group;
import com.example.tank.demo1.netty.message.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

public class TankMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 18) {
            return;
        }
        int x = byteBuf.readInt();
        int y = byteBuf.readInt();
        Dir dir = Dir.values()[byteBuf.readInt()];
        boolean moving = byteBuf.readBoolean();
        boolean living = byteBuf.readBoolean();
        Group group = Group.values()[byteBuf.readInt()];
        UUID id = new UUID(byteBuf.readLong(),byteBuf.readLong());
//        int oldX = byteBuf.readInt();
//        int oldY = byteBuf.readInt();
        list.add(new TankJoinMsg(x,y,dir,moving,living,group,id));
//        list.add(new TankMsg(x,y,living,oldX,oldY));
    }
}
