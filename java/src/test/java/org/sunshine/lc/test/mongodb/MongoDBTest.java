package org.sunshine.lc.test.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class MongoDBTest {

    public static void main(String args[]) {
        // 创建 MongoDB 连接
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        // 连接到 MongoDB
        //MongoCredential credential;
        //credential = MongoCredential.createCredential("test", "test", "123456".toCharArray());
        //System.out.println("Connected to the database successfully");
        // 访问数据库
        MongoDatabase database = mongo.getDatabase("raoyang");
        //System.out.println("Credentials ::"+ credential);

        //创建collection
        database.createCollection("TimeTask");
        //获取collection
        MongoCollection<Document> collection = database.getCollection("TimeTask");

        long curTime = System.currentTimeMillis();

        Ints ints = new Ints();
        Longs longs = new Longs();
        DocumentCreator creator = new DocumentCreator(ints, longs);
        //插入1条记录
        collection.insertMany(creator.create100DocNotSameTime());
        System.out.println("插入一条记录");
        //创建索引
        Document index = new Document("time", 1);
        collection.createIndex(index);
        System.out.println("创建time索引");

        Document index1 = new Document("id", 1);
        collection.createIndex(index1);
        System.out.println("创建id索引");


        long sameTime = curTime;
        //插入5000w记录
        for(int i = 1 ; i < 500000 ; i ++) {
            List<Document> list = creator.create100DocNotSameTime();
            collection.insertMany(list);
            System.out.println("插入第" + i + "批");
        }

        //插入5000w记录
        for(int i = 0 ; i < 500000 ; i ++) {
            List<Document> list = creator.create100DocSameTime();
            collection.insertMany(list);
            System.out.println("插入第" + i + "批");
        }


        /*
        for (String name : database.listCollectionNames()) {
            System.out.println(name);
        }*/
    }
}
