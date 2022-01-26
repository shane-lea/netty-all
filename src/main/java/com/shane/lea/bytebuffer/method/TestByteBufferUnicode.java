package com.shane.lea.bytebuffer.method;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static com.shane.lea.bytebuffer.utils.ByteBufferUtil.debugAll;

@Slf4j
public class TestByteBufferUnicode {


    public static void main(String[] args) {

        /**
         * 字符串转为ByteBuffer
         */
        // byteBuffer 转成字符串
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());
        debugAll(buffer);

        // Charset
        ByteBuffer buffer1 = Charset.forName("utf-8").encode("hello");
        debugAll(buffer1);
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("world");
        debugAll(buffer2);

        // wrap
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer3);

        /**
         * ByteBuffer转为字符串
         */
        String direct = StandardCharsets.UTF_8.decode(buffer).toString();
        log.info("直接转什么也得不到 {}", direct);
        buffer.flip();
        String flip = StandardCharsets.UTF_8.decode(buffer).toString();
        log.info("切换为读模式后  {} = 你懂啥意思了吧", flip);
        String direct3 = StandardCharsets.UTF_8.decode(buffer3).toString();
        log.info("其他的直接转也有 {} = 你懂啥意思了吧", direct3);


    }
}
