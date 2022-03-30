package org.sunshine.lc.test.mongodb;

public class Ints {
    private int value;

    public Ints increment(){
        value ++;
        return this;
    }

    public int getValue() {
        return value;
    }
}
