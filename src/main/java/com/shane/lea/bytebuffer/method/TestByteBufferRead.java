package com.shane.lea.bytebuffer.method;


import lombok.extern.slf4j.Slf4j;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static com.shane.lea.bytebuffer.utils.ByteBufferUtil.debugAll;

@Slf4j
public class TestByteBufferRead {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        // buffer读 1.channel写 2.get
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});

        try (FileChannel channel = new RandomAccessFile("data-read.txt", "rw").getChannel()) {

//            //1.channel写
//            ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("manji");
//            channel.write(buffer1);

            buffer.flip();
            buffer.get(new byte[4]);
            debugAll(buffer);

            /**
             * position = 0;
             * mark = -1;
             */
            buffer.rewind();
            debugAll(buffer);

            log.info("读一个{}", (char) buffer.get());
            log.info("读一个{}", (char) buffer.get());
            buffer.mark();
            log.info("读一个{}", (char) buffer.get());
            log.info("读一个{}", (char) buffer.get());
            buffer.reset();
            log.info("读一个{}", (char) buffer.get());
            log.info("读一个{}", (char) buffer.get());
            debugAll(buffer);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
