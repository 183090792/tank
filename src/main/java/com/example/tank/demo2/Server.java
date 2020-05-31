package com.example.tank.demo2;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress(8888));
//        socket.accept() 会阻塞在这里，直到执行一次
        Socket accept = socket.accept();
        System.out.println("a client connect!");
    }
}
