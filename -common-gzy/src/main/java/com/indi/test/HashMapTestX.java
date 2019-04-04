package com.indi.test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2019/3/8.
 */
public class HashMapTestX {
    volatile String s;
    public static void main(String[] args) {
        ConcurrentMap cm = new ConcurrentHashMap<>();
        cm.put("key1", "value1");
    }
}
