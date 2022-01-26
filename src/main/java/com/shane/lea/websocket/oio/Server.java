package com.shane.lea.websocket.oio;

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

//    // 0-init
//    public static void main(String[] args) throws Exception {
//        /**
//         * 0.新建ByteBuffer
//         * 1.创建服务器
//         * 2.绑定监听端口
//         * 3.等待客户端来连接 SocketChannel 用来与客户端之间通信
//         * 4.接收客户端发送的数据
//         */
//        //0.新建ByteBuffer
//        ByteBuffer buffer = ByteBuffer.allocate(16);
//        // 1.创建服务器
//        ServerSocketChannel ssc = ServerSocketChannel.open();
//        // 2.绑定监听端口
//        ssc.bind(new InetSocketAddress(8088));
//        log.debug("connecting before...");
//        //3.等待客户端来连接 SocketChannel 用来与客户端之间通信
//        SocketChannel channel = ssc.accept(); // 阻塞方法，线程停止运行
//        log.debug("connected end...{}", channel);
//        //4.接收客户端发送的数据
//        channel.read(buffer);
//        buffer.flip();
//        debugRead(buffer);
//    }

    // 1-improve
    public static void main(String[] args) throws Exception {
        /**
         * 0.新建ByteBuffer
         * 1.创建服务器
         * 2.绑定监听端口
         * 3.等待客户端来连接 SocketChannel 用来与客户端之间通信
         * 4.使用连接池集合来收集客户端的连接
         * 5.接收客户端发送的数据
         */
        //0.新建ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1.创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 2.绑定监听端口
        ssc.bind(new InetSocketAddress(8088));
        //4.使用连接池集合来收集客户端的连接
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            log.debug("connecting before...");
            SocketChannel sc = ssc.accept(); // 阻塞方法，线程停止运行
            //3.等待客户端来连接 SocketChannel 用来与客户端之间通信
            log.debug("connected end...{}", sc);

            channels.add(sc);
            for (SocketChannel channel : channels) {
                log.info("read before...{}", channel);
                //5.接收客户端发送的数据
                channel.read(buffer);
                buffer.flip();
                debugRead(buffer);
                buffer.clear(); //
                log.info("read after...{}", channel);

            }

        }


    }


}
