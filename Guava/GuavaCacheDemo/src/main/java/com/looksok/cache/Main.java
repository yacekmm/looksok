package com.looksok.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, Cache!");

        CacheDemo cacheDemo = new CacheDemo(new DataDao());
        cacheDemo.initCache();

        for (int i = 0; i < 35; i++) {
            cacheDemo.getValue("Orange");
            Thread.sleep(1000);
        }
    }
}
