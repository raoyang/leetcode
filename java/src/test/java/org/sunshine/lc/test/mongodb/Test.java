package org.sunshine.lc.test.mongodb;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

public class Test {

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

        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.append("shardCollection", "test.test");
        basicDBObject.append("key", new BasicDBObject("time", 1));
        System.out.println(basicDBObject.toJson());
        Document result = database.runCommand(basicDBObject);
        System.out.println(result);

        /*
        //获取collection
        MongoCollection<Document> collection = database.getCollection("test");
        BasicDBList basicDBList = new BasicDBList();
        basicDBList.add(1);
        basicDBList.add(2);
        basicDBList.add(3);
        basicDBList.add(4);
        basicDBList.add(5);

        BasicDBObject basicDBObject = new BasicDBObject("id", new BasicDBObject("$in", basicDBList));
        DeleteResult result = collection.deleteMany(basicDBObject);
        System.out.println(result.getDeletedCount());*/
    }

}
