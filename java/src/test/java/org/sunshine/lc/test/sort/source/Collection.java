package org.sunshine.lc.test.sort.source;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Collection {

    public static void main(String args[]){
        Integer arr[] = {5,4,3,12,1};
        List<Integer> list = Arrays.asList(arr);
        Collections.sort(list);
        System.out.print(list);
    }
}
