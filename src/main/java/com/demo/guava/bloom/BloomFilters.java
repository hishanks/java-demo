package com.demo.guava.bloom;

/**
 * @author Shanks
 * @date 2019-05-22
 */
public class BloomFilters {

    /**
     * 数组长度
     */
    private int arraySize;

    /**
     * 数组
     */
    private int[] array;

    public BloomFilters(int arraySize) {
        this.arraySize = arraySize;
        array = new int[arraySize];
    }

    /**
     * 写入数据
     *
     * @param key key
     */
    public void add(String key) {
        int first = hashcode1(key);
        int second = hashcode2(key);
        int third = hashcode3(key);
        array[first % arraySize] = 1;
        array[second % arraySize] = 1;
        array[third % arraySize] = 1;
    }

    /**
     * 判断数据是否存在
     *
     * @param key key
     * @return boolean
     */
    public boolean check(String key) {
        int first = hashcode1(key);
        int second = hashcode2(key);
        int third = hashcode3(key);
        int firstIndex = array[first % arraySize];
        if (firstIndex == 0) {
            return false;
        }
        int secondIndex = array[second % arraySize];
        if (secondIndex == 0) {
            return false;
        }
        int thirdIndex = array[third % arraySize];
        return thirdIndex != 0;
    }

    /**
     * hash 算法1
     *
     * @param key key
     * @return int
     */
    private int hashcode1(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }

    /**
     * hash 算法2
     *
     * @param data data
     * @return int
     */
    private int hashcode2(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }

    /**
     * hash 算法3
     *
     * @param key key
     * @return int
     */
    private int hashcode3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash);
    }
}