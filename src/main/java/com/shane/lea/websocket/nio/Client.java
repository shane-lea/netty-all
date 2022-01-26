package com.shane.lea.websocket.nio;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

@Slf4j
public class Client {

    public static void main(String[] args) throws Exception {

        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8888));
        log.info("waiting...{}", sc);

    }
}
