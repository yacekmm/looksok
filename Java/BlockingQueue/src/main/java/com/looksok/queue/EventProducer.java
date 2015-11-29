package com.looksok.queue;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class EventProducer extends Thread {
    private final BlockingQueue<MyEventWorkUnit<MyEvent>> queue;
    private AtomicInteger idGenerator;

    public static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    public EventProducer(BlockingQueue<MyEventWorkUnit<MyEvent>> queue) {
        this.queue = queue;
        idGenerator = new AtomicInteger();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            boolean wasAdded = false;
            MyEvent eventToAdd = new MyEvent(idGenerator.getAndIncrement());
            try {
                wasAdded = queue.offer(new MyEventWorkUnit<>(eventToAdd), 100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                log.info("Adding Thread was interrupted");
            } finally {
                handleResult(wasAdded, eventToAdd);
            }
        }
    }

    private void handleResult(boolean wasAdded, MyEvent eventToAdd) {
        if (wasAdded) {
            log.info("Event was added to the queue: " + eventToAdd.toString());
        } else {
            log.warning("Unable to add event to queue due to timeout!");
        }
    }
}
