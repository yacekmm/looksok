package com.looksok.queue;

import java.util.concurrent.BlockingQueue;

public class EventConsumer extends Thread{
    private final BlockingQueue<MyEventWorkUnit<MyEvent>> queue;
    boolean running = true;

    public EventConsumer(BlockingQueue<MyEventWorkUnit<MyEvent>> queue) {
        this.queue = queue;
    }

    public synchronized void shutdown() {
        this.running = false;
    }

    @Override
    public void run() {
        while(running){
            try {
                MyEventWorkUnit<MyEvent> workUnit = queue.take();
                workUnit.getWorkUnit().print();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
