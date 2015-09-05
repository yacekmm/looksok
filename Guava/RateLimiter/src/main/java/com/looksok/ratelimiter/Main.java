package com.looksok.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

public class Main {

    public static void main(String args[]){
        System.out.println("Welcome to the RateLimiter Demo!\n");

        LongRunningProcess process = new LongRunningProcess(10, new ProgressGuiUpdater(), RateLimiter.create(2));
        new Thread(process).run();
    }
}
