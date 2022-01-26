package com.shane.lea.bytebuffer.method;


import java.nio.ByteBuffer;

public class TestByteBufferAllocate {

    public static void main(String[] args) {

        /**
         * class java.nio.HeapByteBuffer
         * 堆内存ByteBuffer
         * 效率较低收到Java GC影响
         */
        System.out.println(ByteBuffer.allocate(10).getClass());
        /**
         * class java.nio.DirectByteBuffer
         * 直接内存ByteBuffer
         * 效率较高 少一次拷贝 不受GC影响
         * 调用系统函数 分配效率低 也可能出现内存泄露
         */
        System.out.println(ByteBuffer.allocateDirect(16).getClass());

    }
}
