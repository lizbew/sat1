package com.viifly.wba.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.viifly.wba.Configuration;
import org.bson.Document;

import java.util.Map;

public class MongoService {
    private MongoClient mongoClient;
    private MongoDatabase database;

    private String connectionString;
    private String dbName;
    private String user = "test";
    private String password = "test";

    public void init(Configuration config) {
        connectionString = config.getValue("mongodb.url");
        dbName = config.getValue("mongodb.dbname");

        // MongoCredential credential = MongoCredential.createCredential(user, "admin", password.toCharArray());

        MongoClientURI clientURI = new MongoClientURI(connectionString);
        mongoClient = new MongoClient(clientURI);
        database = mongoClient.getDatabase(dbName);
    }

    public void saveMap(String collectionName, Map<String, String> data) {
        Document doc = new Document();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            doc.append(entry.getKey(), entry.getValue());
        }

        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(doc);
    }

    /*
    public static class MongoServiceManager {
        static MongoService instance = new MongoService();
    }

    public static MongoService getInstance() {
        MongoService service = MongoServiceManager.instance;
        if (service.mongoClient == null) {
            service.init("mongo://localhost:27017");
        }
        return service;
    }
    */

    public int increasePageView(String pageUrl) {
        return 1;
    }
}
