package com.looksok.queue;

public class MyEventWorkUnit<T> {

    private T myEventWorkUnit;

    public MyEventWorkUnit(T myEventWorkUnit) {
        this.myEventWorkUnit = myEventWorkUnit;
    }

    public T getWorkUnit() {
        return myEventWorkUnit;
    }
}
