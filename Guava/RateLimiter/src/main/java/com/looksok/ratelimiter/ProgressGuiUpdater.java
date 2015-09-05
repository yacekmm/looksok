package com.looksok.ratelimiter;

import java.time.LocalDateTime;

public class ProgressGuiUpdater implements GuiUpdater{
    @Override
    public void updateGui(String message) {
        System.out.println(LocalDateTime.now().toLocalTime() + ": " + message);
    }
}

