package com.looksok.ratelimiter;

public class LongRunningProcess implements Runnable{

    private int stepsCount;
    private GuiUpdater guiUpdater;

    public LongRunningProcess(int stepsCount, GuiUpdater guiUpdater) {
        this.stepsCount = stepsCount;
        this.guiUpdater = guiUpdater;
    }

    @Override
    public void run() {
        for (int i = 0; i < stepsCount; i++) {
            guiUpdater.updateGui(progerss is);
        }
    }
}
