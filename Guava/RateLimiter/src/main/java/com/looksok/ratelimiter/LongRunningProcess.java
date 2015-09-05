package com.looksok.ratelimiter;

import java.math.BigDecimal;

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
            guiUpdater.updateGui("progress is " + round((float)i/stepsCount * 100, 2) + "%");
        }
    }

    private BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        return bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
    }
}
