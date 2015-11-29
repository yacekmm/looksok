package com.looksok.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) {

        BlockingQueue<MyEventWorkUnit<MyEvent>> queue = new ArrayBlockingQueue<>(1);

        EventProducer producer = new EventProducer(queue);
        EventConsumer consumer = new EventConsumer(queue);

        producer.start();
        consumer.start();
    }
}
