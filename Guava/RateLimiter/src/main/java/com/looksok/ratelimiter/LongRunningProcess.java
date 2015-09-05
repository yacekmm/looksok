package com.looksok.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.math.BigDecimal;

public class LongRunningProcess implements Runnable{

    private int stepsCount;
    private GuiUpdater guiUpdater;
    private RateLimiter rateLimiter;

    public LongRunningProcess(int stepsCount, GuiUpdater guiUpdater, RateLimiter rateLimiter) {
        this.stepsCount = stepsCount;
        this.guiUpdater = guiUpdater;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < stepsCount; i++) {
            double waitTime = rateLimiter.acquire();
            guiUpdater.updateGui("This is the item number " + i + " (I've waited for acquire for " + waitTime + " seconds)");
        }
    }
}
