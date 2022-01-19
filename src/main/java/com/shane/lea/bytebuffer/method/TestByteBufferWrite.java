package com.shane.lea.bytebuffer.method;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

import static com.shane.lea.bytebuffer.utils.ByteBufferUtil.debugAll;

@Slf4j
public class TestByteBufferWrite {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        debugAll(buffer);  // position 从零开始，
        // 写入数据
        buffer.put((byte) 0x64);
        debugAll(buffer);
        buffer.put(new byte[]{0x65, 0x66, 0x67});
        debugAll(buffer);

//        log.info("不切换直接读:{} == 读position下标位", buffer.get());
        debugAll(buffer);
        /**
         * limit = position;
         * position = 0;
         * mark = -1;
         */
        buffer.flip();
        log.info("切换后读取:{} == 数据仍然在内存中,position下标位移动", buffer.get());
        debugAll(buffer);
        buffer.compact(); // java.nio.HeapByteBuffer.compact
        /**
         * 数据向前压缩后 后面位子的数据并不清零
         * 不仅仅是这样简单的理解 详细见java.nio.HeapByteBuffer.compact
         */
        debugAll(buffer);


    }

}
