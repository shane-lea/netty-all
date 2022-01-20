package com.shane.lea.bytebuffer.method;


import java.nio.ByteBuffer;

public class TestByteBufferAllocate {

    public static void main(String[] args) {

        /**
         * class java.nio.HeapByteBuffer
         * 堆内存ByteBuffer
         */
        System.out.println(ByteBuffer.allocate(10).getClass());
        /**
         * class java.nio.DirectByteBuffer
         * 直接内存ByteBuffer
         */
        System.out.println(ByteBuffer.allocateDirect(16).getClass());

    }
}
