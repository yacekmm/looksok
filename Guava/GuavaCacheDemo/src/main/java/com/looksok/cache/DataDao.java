package com.looksok.cache;

import java.time.LocalTime;

/**
 * Created by Jacek Milewski (looksok.wordpress.com)
 */
public class DataDao {

    public String getValueForKey(String key) {
        String toReturn = "value for key " + key + ", accessed at " + LocalTime.now();
        System.out.println("returning value from dao: " + toReturn);
        return toReturn;
    }
}
