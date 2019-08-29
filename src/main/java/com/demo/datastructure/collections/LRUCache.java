package com.demo.datastructure.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LRU算法的缓存，定长的链表中，剔除最老插入的数据（默认情况下链表头部数据、最近访问的数据移到链表尾部）
 * 继承LinkedHashMap，重写removeEldestEntry方法，实现定长Entry的LinkedHashMap
 * 若插入键值对超过size个后，继续添加一个新的键的话，则会删除最早添加的那条数据
 * 应用场景，可以想象的是，可以使用这个类作为缓存，LRU（least recently used ，最近最少使用)
 * 缓存算法，比较常见的是三种：
 * 1. LRU（least recently used ，最近最少使用)
 * 2. LFU（Least Frequently used ，最不经常使用)
 * 3. FIFO（first in first out ，先进先出)
 *
 * @author Shanks
 * @date 2019-06-18
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 5648431962704386663L;
    private transient int size;
    private static final boolean ACCESS_ORDER = true;
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int THRESHOLD = ((int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR));

    /**
     * 使用该无参构造时，最好是缓存最大数据条数为11，即16*0.75-1
     */
    public LRUCache() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, THRESHOLD - 1);
    }

    /**
     * @param initialCapacity 初始化容量（不必多说，2^n）
     * @param size            缓存键值对数量（最好为：initialCapacity * 0.75 - 1）
     */
    public LRUCache(int initialCapacity, int size) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, size);
    }

    /**
     * 使用该全参构造方法时，建议根据阈值来设置size的大小，其中size值最好设置为（阈值-1）
     * 例如：初始化容量大小为8，负载因子为0.75，则扩容阈值为8*0.75，即6，表示继续存储第7条数据时，该数据结构会扩容，所以设置该条件下size为5
     * 假如此时就希望最大存储6条数据，那么建议设置初始化容量为16，此时阈值为12，以空间换时间，避免了扩容
     *
     * @param initialCapacity 初始化容量
     * @param loadFactor      负载因子
     * @param size            缓存最大存储数量
     */
    public LRUCache(int initialCapacity, float loadFactor, int size) {
        // true for access-order, false for insertion-order 访问顺序和插入顺序
        // 最近访问的数据，移到双向链表的尾部
        super(initialCapacity, loadFactor, ACCESS_ORDER);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 键值对数大于指定的缓存个数的时候，就自动删除最老的数据，即最先插入的那条数据
        return super.size() > size;
    }
}