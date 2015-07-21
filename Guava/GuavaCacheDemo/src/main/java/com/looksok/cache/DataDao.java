package com.looksok.cache;

import java.time.LocalTime;

public class DataDao {

    public String getValueForKey(String key) {
        String toReturn = "value for key " + key + ", refreshed from DAO at " + LocalTime.now();
        System.out.println("returning value from dao: " + toReturn);
        return toReturn;
    }
}
