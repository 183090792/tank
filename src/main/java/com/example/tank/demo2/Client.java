package com.example.tank.demo2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;

public class Client {
    public static void main(String[] args) {
            NioEventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture future = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", 8888);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(!channelFuture.isSuccess()){
                        System.out.println("not connected!");
                    }else {
                        System.out.println("connected!");
                    }
                }
            });
            future.sync();

            System.out.println("....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println(socketChannel);
    }
}
