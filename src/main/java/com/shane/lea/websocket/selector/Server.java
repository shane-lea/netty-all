package com.shane.lea.websocket.selector;

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

    public static void main(String[] args) throws Exception{

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));
        List<SocketChannel> channels = new ArrayList<>();

        while (true) {
            SocketChannel sc = ssc.accept(); //非阻塞 咩有连接返回null
            if (sc != null) {
                sc.configureBlocking(false);  //非阻塞
                log.info("connecting... {}", sc);
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
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
