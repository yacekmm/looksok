package com.looksok.future;

import java.time.LocalDate;

public class ListenableFutureDemo {
    public String executeLongRunningTask() throws InterruptedException {
        Thread.sleep(5000);
        return "Evaluation finished at " + LocalDate.now();
    }
}
