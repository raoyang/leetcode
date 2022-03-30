package org.sunshine.lc.test.mongodb;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.Iterator;

public class MongoSortTest {

    public static void main(String args[]){
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

        Document sortDoc = new Document("time", 1);

        int count = 0;
        boolean isEmpty = false;
        do{
            FindIterable<Document> findIterable = collection.find().limit(10000).sort(sortDoc);
            if(findIterable.first() == null) {
                isEmpty = true;
            }
            Iterator<Document> iterator = findIterable.iterator();
            long time = System.currentTimeMillis();
            BasicDBList basicDBList = new BasicDBList();
            while(iterator.hasNext()){
                Document document = iterator.next();
                //System.out.println(document.get("id"));
                basicDBList.add(document.get("id"));
            }
            BasicDBObject basicDBObject = new BasicDBObject("id", new BasicDBObject("$in", basicDBList));
            DeleteResult deleteResult = collection.deleteMany(basicDBObject);
            long end = System.currentTimeMillis();
            count ++;
            System.out.println("输出1w条记录:" + (end - time) + ", count:" + count + ", 删除数量:" + deleteResult.getDeletedCount());

        }while (!isEmpty);
    }
}
