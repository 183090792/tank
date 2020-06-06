package com.example.tank.netty;

import com.example.tank.netty.message.Msg;
import com.example.tank.netty.message.TankMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TankMsgEncoder extends MessageToByteEncoder<TankMsg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TankMsg tankMsg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(tankMsg.getX());
        byteBuf.writeInt(tankMsg.getY());
        byteBuf.writeBoolean(tankMsg.living);
//        byteBuf.writeInt(tankMsg.getOldX());
//        byteBuf.writeInt(tankMsg.getOldY());
    }
}
