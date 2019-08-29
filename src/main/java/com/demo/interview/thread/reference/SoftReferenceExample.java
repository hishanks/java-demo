package com.demo.interview.thread.reference;

import sun.jvm.hotspot.utilities.BitMap;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shanks
 * @date 2019-06-17
 */
public class SoftReferenceExample {

    public static void main(String[] args) {
        /*
         * 加载大量图片，使用hashMap来保存图片的路径和相应图片关联的软引用之间的映射关系
         * 在内存不足时，JVM会自动回收这些缓存图片对象所占用的空间，从而有效避免了OOM的问题
         */
        Map<String, SoftReference<BitMap>> imageCache = new HashMap<>(16);
    }
}