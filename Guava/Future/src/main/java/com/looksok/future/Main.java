package com.looksok.future;

import com.google.common.util.concurrent.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(LocalTime.now() + " Hello future!");

        final ListenableFutureDemo demo = new ListenableFutureDemo();

        //create executor service to submit all future tasks
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        //execute non-blocking task
        ListenableFuture<String> listenableFuture = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return demo.executeLongRunningTask();
            }
        });

        //add success and failure handler to listenableFuture object
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(LocalTime.now() + " Task completed successfuly with result: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(LocalTime.now() + " Task failed with result: " + t.getMessage());
            }
        });

//        String result = demo.executeLongRunningTask();

        System.out.println(LocalTime.now() + " Main method completed");
    }
}
