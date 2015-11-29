package com.looksok.queue;

import java.util.concurrent.BlockingQueue;

public class EventConsumer extends Thread{
    private final BlockingQueue<MyEventWorkUnit<MyEvent>> queue;

    public EventConsumer(BlockingQueue<MyEventWorkUnit<MyEvent>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                MyEventWorkUnit<MyEvent> workUnit = queue.take();
                workUnit.getWorkUnit().handle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
