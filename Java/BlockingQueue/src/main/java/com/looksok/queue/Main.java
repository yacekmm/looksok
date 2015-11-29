package com.looksok.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {

        BlockingQueue<MyEventWorkUnit<MyEvent>> queue = new LinkedBlockingQueue<>();
        System.out.println("hello");
    }
}
