package com.shanelea.netty.c1;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {

    public static void main(String[] args) {

        // FileChannel
        // 1.输入输出流  2.RandomAccessFile
        try (FileChannel fileChannel = new FileInputStream("data.txt").getChannel()){

            ByteBuffer allocate = ByteBuffer.allocate(10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
