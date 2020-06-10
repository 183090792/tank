package com.example.tank.demo1.netty;

import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Group;
import com.example.tank.demo1.Tank;
import com.example.tank.demo1.TankFrame;
import com.example.tank.demo1.netty.message.Msg;
import com.example.tank.demo1.netty.message.TankJoinMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/6/5 9:56
 */
@Slf4j
public class Client {
    public static final Client CLIENT = new Client();
    private Client(){}
    private Channel channel;
    public void connect(){
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture future = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new TankMsgEncoder())
                                    .addLast(new TankMsgDecoder())
                                    .addLast(new ClientChannelAdapter());
                        }
                    }).connect("localhost", 8888)
                    .sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        channel = channelFuture.channel();
                        System.out.println((Thread.currentThread().getName() + " Client connected success!"));
                    }else {
                        System.out.println((Thread.currentThread().getName() + " Client connected fail!"));
                    }
                }
            });
            new Thread(()->{
                    while (true){
                        try {
                            Thread.sleep(10);
                            TankFrame.TANK_FRAME.repaint();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }).start();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public void send(Msg msg){

        ByteBuf buf = Unpooled.copiedBuffer(msg.toBytes());
        channel.writeAndFlush(buf);
    }
}

@Slf4j
class ClientChannelAdapter extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Tank tank = TankFrame.TANK_FRAME.getMainTank();
//        tank.setGroup(Group.BAD);
        TankJoinMsg tankJoinMsg = new TankJoinMsg(tank);
        System.out.println("Client UUID:"+tankJoinMsg.getId());
        ctx.writeAndFlush(tankJoinMsg);
//        Random r = new Random();
//        ByteBuf buf = Unpooled.copiedBuffer((Thread.currentThread().getName()+"hello").getBytes());
//        ctx.channel().writeAndFlush(TankFrame.TANK_FRAME.getMainTank());
//        ctx.writeAndFlush(new TankJoinMsg(r.nextInt(TankFrame.WIDTH),r.nextInt(TankFrame.HEIGHT),Dir.UP,false,Group.BAD, UUID.randomUUID()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接收到消息");
        TankJoinMsg tankMsg = (TankJoinMsg) msg;
        if(TankFrame.TANK_FRAME.tanks.get(tankMsg.getId())==null && !TankFrame.TANK_FRAME.tank.getId().equals(tankMsg.getId())){
            TankFrame.TANK_FRAME.tanks.put(tankMsg.getId(),new Tank(tankMsg.getX(),tankMsg.getY(),tankMsg.getDir(),tankMsg.isMoving(),TankFrame.TANK_FRAME,true,tankMsg.getGroup(),tankMsg.getId()));
        }
        log.debug("接收服务端信息："+TankFrame.TANK_FRAME.tanks.size());
//        tankFrame.tanks.add(new Tank(50+i*80,200, Dir.DOWN,true,tankFrame,true, Group.BAD));
//        ByteBuf buf = (ByteBuf)msg;
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.getBytes(buf.readerIndex(),bytes);
//        System.out.println(new String(bytes));
//        ClientFrame.CLIENT_FRAME.updateText(new String(bytes));
//        if(buf!=null){
//            ReferenceCountUtil.release(buf);
//        }
    }
}
