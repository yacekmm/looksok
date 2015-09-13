package com.looksok.future;

import java.time.LocalTime;

public class ListenableFutureDemo {
    public String executeLongRunningTask() throws InterruptedException {
        System.out.println(LocalTime.now() + " Starting evaluating long running tasks...");
        Thread.sleep(5000);
        return "Evaluation finished at " + LocalTime.now();
    }
}
