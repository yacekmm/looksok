package com.looksok.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Created by Jacek Milewski (looksok.wordpress.com)
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, Cache!");

        CacheDemo cacheDemo = new CacheDemo(new DataDao());
        cacheDemo.initCache();
        cacheDemo.getValue("Orange");
        cacheDemo.getValue("Orange");
        cacheDemo.getValue("Orange");
        cacheDemo.getValue("Orange");
    }
}
