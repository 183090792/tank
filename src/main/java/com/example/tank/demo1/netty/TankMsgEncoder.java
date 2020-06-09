package com.example.tank.demo1.netty;

import com.example.tank.demo1.netty.message.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TankMsgEncoder extends MessageToByteEncoder<TankJoinMsg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TankJoinMsg tankMsg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(tankMsg.getX());
        byteBuf.writeInt(tankMsg.getY());
        byteBuf.writeBoolean(tankMsg.living);
//        byteBuf.writeInt(tankMsg.getOldX());
//        byteBuf.writeInt(tankMsg.getOldY());
    }
}
