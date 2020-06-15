package com.example.tank.demo1.netty;

import com.example.tank.demo1.Tank;
import com.example.tank.demo1.netty.message.Msg;
import com.example.tank.demo1.netty.message.MsgType;
import com.example.tank.demo1.netty.message.TankJoinMsg;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/6/5 10:50
 */
public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Map<UUID, TankJoinMsg> tankJoinMsgMap = new HashMap<>();

    public void serverStart(){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(4);
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            ChannelFuture future = bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new TankMsgEncoder())
                                    .addLast(new TankMsgDecoder())
                                    .addLast(new MsgHandler());
//                                    .addLast(new ServerChannelAdapter());
                        }
                    }).bind(8888)
                    .sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class ServerChannelAdapter extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ServerFrame.INSTANCE.updateClientMsg(msg.toString());
        TankJoinMsg tankMsg = (TankJoinMsg) msg;
        Server.tankJoinMsgMap.put(tankMsg.getId(),tankMsg);
        Collection<TankJoinMsg> values = Server.tankJoinMsgMap.values();
        for (TankJoinMsg value : values) {
            Server.clients.writeAndFlush(value);
        }
//        System.out.println(tankMsg);
////        ByteBuf buf = (ByteBuf)msg;
////        byte[] bytes = new byte[buf.readableBytes()];
////        buf.getBytes(buf.readerIndex(),bytes);
//        for (Channel client : Server.clients) {
//            if(!client.id().equals(ctx.channel().id())){
//                client.writeAndFlush(msg);
//            }
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }
}

class MsgHandler extends SimpleChannelInboundHandler<Msg>{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {

        ServerFrame.INSTANCE.updateClientMsg(msg.toString());
        if(msg.getMsgType().equals(MsgType.TankJoin)){
            TankJoinMsg tankMsg = (TankJoinMsg) msg;
            Server.tankJoinMsgMap.put(tankMsg.getId(),tankMsg);
            Collection<TankJoinMsg> values = Server.tankJoinMsgMap.values();
            for (TankJoinMsg value : values) {
                System.out.println("server UUID:"+value.getId());
                Server.clients.writeAndFlush(value);
            }
        }else {
            Server.clients.writeAndFlush(msg);
        }
//        try {
////            ByteBuf buf = (ByteBuf)msg;
//
//            TankJoinMsg tankMsg = (TankJoinMsg) msg;
//            System.out.println(tankMsg);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            ReferenceCountUtil.release(msg);
//        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }
}
