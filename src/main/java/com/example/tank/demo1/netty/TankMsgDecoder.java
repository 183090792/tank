package com.example.tank.demo1.netty;

import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Group;
import com.example.tank.demo1.netty.message.Msg;
import com.example.tank.demo1.netty.message.MsgType;
import com.example.tank.demo1.netty.message.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.message.ReusableMessage;

import java.util.List;
import java.util.UUID;

public class TankMsgDecoder extends ByteToMessageDecoder {
    /**
     * 1、先判断 消息类型、消息长度 是否传输完成
     * 2、再判断消息长度是否满足要求，不满足将 byteBuf 读位置复位
     * 3、解析消息内容
     *
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 8) {
            return;
        }
        byteBuf.markReaderIndex();
        System.out.println("TankMsgDecoder 1、"+byteBuf.readInt()+"   2、"+byteBuf.readInt());
        MsgType msgType = null;
        msgType = MsgType.values()[byteBuf.readInt()];
        int length = byteBuf.readInt();
        if (byteBuf.readableBytes() < length) {
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Msg msg = null;
        msg = (Msg) Class.forName("com.example.tank.demo1.netty.message." + msgType.toString() + "Msg").getDeclaredConstructor().newInstance();
        msg.parse(bytes);
        list.add(msg);






        /*if (byteBuf.readableBytes() < 18) {
            return;
        }
        int x = byteBuf.readInt();
        int y = byteBuf.readInt();
        Dir dir = Dir.values()[byteBuf.readInt()];
        boolean moving = byteBuf.readBoolean();
//        boolean living = byteBuf.readBoolean();
        Group group = Group.values()[byteBuf.readInt()];
        UUID id = new UUID(byteBuf.readLong(), byteBuf.readLong());
//        int oldX = byteBuf.readInt();
//        int oldY = byteBuf.readInt();
        list.add(new TankJoinMsg(x, y, dir, moving, group, id));
//        list.add(new TankMsg(x,y,living,oldX,oldY));*/
    }
}
