package org.sunshine.lc.test.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoTest {

    public static void main(String args[]) {
        // 创建 MongoDB 连接
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        // 连接到 MongoDB
        MongoCredential credential;
        credential = MongoCredential.createCredential("test", "test", "123456".toCharArray());
        System.out.println("Connected to the database successfully");
        // 访问数据库
        MongoDatabase database = mongo.getDatabase("test");
        System.out.println("Credentials ::"+ credential);
        //获取collection
        MongoCollection<Document> collection = database.getCollection("test");
        collection.drop();

    }
}
