package org.sunshine.lc.test.timer.rbtree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.UUID;

public class RBTreeTimer {

    private TreeMap<Long, LinkedList<String>> treeMap;

    public RBTreeTimer(){
        treeMap = new TreeMap<>();
    }

    public void add(long seconds, String value) {
        LinkedList<String> linkedList = treeMap.get(seconds);
        if(linkedList == null){
            linkedList = new LinkedList<>();
            treeMap.put(seconds, linkedList);
        }
        linkedList.add(value);
    }

    public void cancel(long seconds, String value) {
        LinkedList<String> linkedList = treeMap.get(seconds);
        if(linkedList == null){
            return;
        }
        linkedList.remove(value);
    }

    public LinkedList<String> getValues(){
        return treeMap.get(treeMap.firstKey());
    }

    public Long getFirstKey(){
        if(!treeMap.isEmpty()){
            Long key = treeMap.firstKey();
            treeMap.remove(key);
            return key;
        }else{
            return null;
        }
    }

    public static void main(String args[]){
        RBTreeTimer rbTreeTimer = new RBTreeTimer();
        String uuid = null;
        HashSet<String> values = new HashSet<>();
        for(int i = 0 ; i < 100000 ; i ++){
            String value = UUID.randomUUID().toString();
            rbTreeTimer.add(i, value);
            values.add(value);
            uuid = value;
        }

        Long key = rbTreeTimer.getFirstKey();
        while(key != null){
            System.out.println(key);
            key = rbTreeTimer.getFirstKey();
        }
    }
}
