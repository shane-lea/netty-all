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

        /**
         * 1.创建selector 管理多个channel
         * 2.建立selector跟channel的联系 （把channel注册到selector）
         * 3.获得事件key (事件的类型: accept[有连接请求时触发]/connect[客户端连接后触发]/read[可读事件]/write[可写事件])
         * 4.绑定兴趣事件
         * 5.selector.select 线程阻塞,有事件才继续运行
         * 6.获取事件集合
         * 7.遍历 获取事件绑定的channel
         *
         * 2-1.事件要么处理要么取消 不能置之不理 selector事件未处理不会阻塞
         * 3-1.处理完的key一定要移除 不然下次处理key上没有事件 会报空指针
         * 4-1.客户端被强制断开 / 客户端正常断开 *.close 总会产生读事件
         * 5-1.消息边界
         *    1⃣️ 消息边界问题是如何出现的？出现有哪几种情况？
         *    2⃣️ 半包黏包的方案怎么去解决？ 长度+消息体/
         *    3⃣️
         *    4⃣️
         *
         */


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
