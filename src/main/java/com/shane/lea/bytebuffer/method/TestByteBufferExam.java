package com.shane.lea.bytebuffer.method;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

import static com.shane.lea.bytebuffer.utils.ByteBufferUtil.debugAll;

/**
 * 网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
 * 但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
 * <p>
 * * Hello,world\n
 * * I'm zhangsan\n
 * * How are you?\n
 * <p>
 * 变成了下面的两个 byteBuffer (黏包，半包)
 * <p>
 * * Hello,world\nI'm zhangsan\nHo
 * * w are you?\n
 * <p>
 * 现在要求你编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
 */
@Slf4j
public class TestByteBufferExam {


    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);

        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }


    private static void split(ByteBuffer source) {
        /**
         * 解决思路
         * 循环
         * 1.找到\n
         * 2.读取至\n
         */
        //开启读
        source.flip();

        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {

            if (source.get(i) == '\n') {
                // 创建同等大小的ByteBuffer缓冲区, 计算逻辑（换行符索引+1-起始字符的索引）
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                source.limit(i + 1);
                target.put(source);
                debugAll(target);
                source.limit(oldLimit);
            }
        }
        source.compact();
    }


}
