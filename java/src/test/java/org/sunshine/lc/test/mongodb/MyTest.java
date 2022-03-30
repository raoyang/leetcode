package org.sunshine.lc.test.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MyTest {

    public static void main(String args[]){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("raoyang");
        //获取collection
        MongoCollection<Document> collection = database.getCollection("TimeTask");
        System.out.println(collection);
    }
}
