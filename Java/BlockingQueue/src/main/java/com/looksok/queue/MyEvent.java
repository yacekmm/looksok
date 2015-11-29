package com.looksok.queue;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

public class MyEvent {

    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getTypeName());

    private final int eventId;

    public MyEvent(int eventId) {
        this.eventId = eventId;
    }

    public void handle() {
        log.info("Serving event with id: " + eventId);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "eventId=" + eventId +
                '}';
    }
}
