package com.looksok.future;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log("Hello, CompletableFuture!");

        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            log("Starting task in CompletableFuture");
            String completionTime = executeLongRunningProcess();
            log("Future task completed at: " + completionTime);
            return completionTime;
        });

        String result = (String) future.get();
        log("get result is: " + result);
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
