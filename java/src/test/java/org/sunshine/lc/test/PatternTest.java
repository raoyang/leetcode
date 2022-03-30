package org.sunshine.lc.test;

import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String args[]){

        String s = "ab";
        boolean result = Pattern.matches(".*", s);
        System.out.print(result);
    }
}
