package org.sunshine.lc.test.mongodb;

public class Longs {

    private long value;
    private long sameValue;

    public Longs(){
        this.value = System.currentTimeMillis();
        this.sameValue = value;
    }

    public Longs increment(){
        value ++;
        return this;
    }

    public long getValue() {
        return value;
    }

    public long getSameValue(){
        return this.sameValue;
    }
}
