package com.shane.lea;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) {
        /**
         * 获取FileChannel的方式
         * 1.输入输出流 2.RandomAccessFile
         */
        try (FileChannel fileChannel = new FileInputStream("data.txt").getChannel()) {

            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(5);

            while (true) {
                //从channel读 写到buffer
                int read = fileChannel.read(buffer);
                log.info("获取到的字节数为:{}", read);
                if (read == -1) {
                    break;
                }
                //切换buffer为读模式
                buffer.flip();
                while (buffer.hasRemaining()) {
                    //打印buffer
                    byte b = buffer.get();
                    log.info("字节为:{}", (char)b);
                }
                //切换buffer为写模式
                buffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
