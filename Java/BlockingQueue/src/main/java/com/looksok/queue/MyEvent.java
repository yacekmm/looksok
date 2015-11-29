package com.looksok.queue;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

public class MyEvent {

    private final int eventId;

    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    public MyEvent(int eventId) {
        this.eventId = eventId;
    }

    public void handle() {
        log.info("Serving event with id: " + eventId);
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "eventId=" + eventId +
                '}';
    }
}
