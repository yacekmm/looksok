package com.looksok.future;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        log("Starting the demo");

        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            log("Starting task in CompletableFuture");
            String completionTime = executeLongRunningProcess();
            log("Future task completed at: " + completionTime);
            return completionTime;
        });

        log("Continuing main thread execution");

        Thread.sleep(1000);

        String result = (String) future.get();
//        String result = (String) future.getNow("Task not yet completed");
//        String result = (String) future.get(3, TimeUnit.SECONDS);
        log("Future get result is: " + result);
    }

    private static String executeLongRunningProcess() {
        try {
            Thread.sleep(5000);
            return LocalTime.now().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
    }

    private static void log(String message) {
        System.out.println(LocalTime.now().toString() + ": " + message);
    }
}
