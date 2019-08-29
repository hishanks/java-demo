package com.demo.nio;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * @author Shanks
 * @date 2018-10-31
 */
public class BufferTest {

    @Test
    void testBuffer() {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("==========>allocate");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        String s = "abcde";
        byteBuffer.put(s.getBytes());
        System.out.println("==========>put");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        byteBuffer.flip();
        System.out.println("==========>flip");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        byte[] dst = new byte[s.length()];
        byteBuffer.get(dst);
        System.out.println("==========>get");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        // 可重复读
        byteBuffer.rewind();
        System.out.println("==========>rewind");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        // 清空缓冲区，但是缓冲区的数据依然存在，处于"被遗忘"状态
        byteBuffer.clear();
        System.out.println("==========>clear");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

    }
}
