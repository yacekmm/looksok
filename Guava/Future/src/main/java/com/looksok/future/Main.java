package com.looksok.future;

import java.time.LocalTime;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(LocalTime.now() + " Hello future!");

        ListenableFutureDemo demo = new ListenableFutureDemo();

        demo.executeLongRunningTask();

        System.out.println(LocalTime.now() + " Main method completed!");
    }
}
