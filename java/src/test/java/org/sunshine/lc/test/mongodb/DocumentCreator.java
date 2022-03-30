package org.sunshine.lc.test.mongodb;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentCreator {

    private Ints ints;
    private Longs longs;
    public DocumentCreator(Ints ints, Longs longs){
        this.ints = ints;
        this.longs = longs;
    }

    public List<Document> create100DocSameTime(){
        List<Document> list = new ArrayList<>(100);
        for(int i = 0 ; i < 100; i ++){
            list.add(getDocumentSameTime());
        }
        return list;
    }

    public List<Document> create100DocNotSameTime(){
        List<Document> list = new ArrayList<>(100);
        for(int i = 0 ; i < 100; i ++){
            list.add(getDocumentNotSameTime());
        }
        return list;
    }

    private Document getDocumentSameTime(){
        return new Document("id", ints.increment().getValue())
                .append("description", "database")
                .append("likes", 100)
                .append("url", "http://www.biancheng.net/mongodb/")
                .append("by", "编程帮")
                .append("time", longs.getSameValue());

    }

    private Document getDocumentNotSameTime(){
        return new Document("id", ints.increment().getValue())
                .append("description", "database")
                .append("likes", 100)
                .append("url", "http://www.biancheng.net/mongodb/")
                .append("by", "编程帮")
                .append("time", longs.increment().getValue());

    }

    public static void main(String args[]){
    }

}
