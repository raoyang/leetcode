package org.sunshine.lc.test.mongodb;

import java.util.Date;

public class TimeTest {

    public static void main(String args[]) {
        long time = System.currentTimeMillis() + 63 * 60 * 1000;
        System.out.println(time);
    }
}
