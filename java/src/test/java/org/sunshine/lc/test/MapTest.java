package org.sunshine.lc.test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String args[]){
        System.out.println('5' - '0');
    }

    private static void mapTest(){
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0 ; i < 10000 ; i ++){
            map.put("aaa" + i, new Integer(i));
        }
    }
}
