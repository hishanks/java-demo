package com.demo.guava.collections.multivaluemap.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

/**
 * +------------------------------------------------------+
 * | 实现        	        Key  	        Value         |
 * +------------------------------------------------------+
 * | ArrayListMultimap	    HashMap	        ArrayList     |
 * +------------------------------------------------------+
 * | HashMultimap	        HashMap	        HashSet       |
 * +------------------------------------------------------+
 * | LinkedListMultimap     LinkedHashMap	LinkedList    |
 * +------------------------------------------------------+
 * | LinkedHashMultimap	    LinkedHashMap	LinkedHashSet |
 * +------------------------------------------------------+
 * | TreeMultimap	        TreeMap 	    TreeSet       |
 * +------------------------------------------------------+
 * | ImmutableListMultimap  ImmutableMap    ImmutableList |
 * +------------------------------------------------------+
 * | ImmutableSetMultimap	ImmutableMap    ImmutableSet  |
 * +------------------------------------------------------+
 *
 * @author Shanks
 * @date 2019-07-10
 */
public class MultiValueMapTest {

    @Test
    void testArrayListMultimap() {
        Multimap<String, String> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("name", "a");
        arrayListMultimap.put("name", "b");
        arrayListMultimap.put("name", "c");
        arrayListMultimap.put("address", "shanghai");
        arrayListMultimap.put("address", "beijing");
        System.out.println(arrayListMultimap);

        Collection<String> name = arrayListMultimap.get("name");
        System.out.println(name);

        // Removing a single value
        arrayListMultimap.remove("name", "mac");
        System.out.println(arrayListMultimap.get("name"));

        // Remove all values for a key
        arrayListMultimap.removeAll("name");
        System.out.println(arrayListMultimap.get("name"));
    }

    /**
     * 一个key对应多value且value不重复，因为其value实现是HashSet
     */
    @Test
    void testHashMultimap() {
        HashMultimap<Integer, String> hashMultimap = HashMultimap.create();
        hashMultimap.put(1, "a");
        hashMultimap.put(1, "a");
        hashMultimap.put(1, "b");
        hashMultimap.put(1, "c");
        hashMultimap.put(2, "d");
        hashMultimap.put(2, "e");
        System.out.println(hashMultimap);
        System.out.println(JSON.toJSONString(hashMultimap));

        Set<String> set = hashMultimap.get(1);
        System.out.println(set);
    }
}