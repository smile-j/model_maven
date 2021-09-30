package com.dong.base.test.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDbTest {

  public static void main(String [] args){
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            System.out.println("Connect to database successfully");
    /*        Document document = new Document("title", "MongoDB").
                    append("description", "database").
                    append("likes", 100).
                    append("by", "Fly");*/
            Document document = new Document("title","mysql")
                    .append("description","关系database")
                    .append("likes","百万级")
                    .append("by","磁盘");
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
//            collection.insertOne(document);
            mongoClient.close();

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }


}
