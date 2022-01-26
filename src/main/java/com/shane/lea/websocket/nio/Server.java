package com.shane.lea.websocket.nio;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.shane.lea.bytebuffer.utils.ByteBufferUtil.debugRead;

@Slf4j
public class Server {

    public static void main(String[] args) throws Exception {

        /**
         * 1. ByteBuffer
         * 2.建立连接
         * 3.绑定端口
         * 4.等待客户端连接 SC用来与客户端的通信
         * 5.用连接池来收集客户端的连接
         * 6.接收客户端发送的数据
         */

        //1
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //2
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        //3
        ssc.bind(new InetSocketAddress(8888));
        //5
        List<SocketChannel> channels = new ArrayList<>();

        while (true) {
            SocketChannel sc = ssc.accept(); //非阻塞 咩有连接返回null
//            log.info("connect before...");
            if (sc != null) {
                sc.configureBlocking(false);  //非阻塞
                log.info("connecting... {}", sc);
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                //6
                int read = channel.read(buffer); // 没有数据返回0
                if (read > 0) {
                    buffer.flip();
                    debugRead(buffer);
                    buffer.clear();
                    log.info("read after...{}", channel);
                }
            }
        }

    }


}
