package com.example.tank.demo1.netty;

import com.example.tank.demo1.netty.message.Msg;
import com.example.tank.demo1.netty.message.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TankMsgEncoder extends MessageToByteEncoder<Msg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Msg msg, ByteBuf byteBuf) throws Exception {
//        1、消息类型
        System.out.println("TankMsgEncoder msg.getMsgType："+msg.getMsgType().ordinal());
        byteBuf.writeInt(msg.getMsgType().ordinal());
//        2、消息长度
        byte[] bytes = msg.toBytes();
        System.out.println("TankMsgEncoder bytes.length ："+bytes.length);
        byteBuf.writeInt(bytes.length);
//        3、数据
        byteBuf.writeBytes(bytes);
//        byteBuf.writeInt(tankMsg.getX());
//        byteBuf.writeInt(tankMsg.getY());
//        byteBuf.writeInt(tankMsg.getDir().ordinal());
//        byteBuf.writeBoolean(tankMsg.isMoving());
////        byteBuf.writeBoolean(tankMsg.isLiving());
//        byteBuf.writeInt(tankMsg.getGroup().ordinal());
//        byteBuf.writeLong(tankMsg.id.getMostSignificantBits());
//        byteBuf.writeLong(tankMsg.id.getLeastSignificantBits());
//        byteBuf.writeInt(tankMsg.getOldX());
//        byteBuf.writeInt(tankMsg.getOldY());
    }
}
