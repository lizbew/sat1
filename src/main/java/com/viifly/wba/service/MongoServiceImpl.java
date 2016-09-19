package com.viifly.wba.service;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MongoServiceImpl implements MongoService{
    private MongoClient mongoClient;
    private MongoDatabase database;

    private String connectionString;
    private String dbName;
    private String user = "test";
    private String password = "test";

    @Autowired
    public void setConfiguration(Configuration config) {
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

    public List<String> listDatabases() {
        return Lists.newArrayList(mongoClient.listDatabaseNames());
    }

    public List<String> listCollections(String database) {
        return Lists.newArrayList(mongoClient.getDatabase(database).listCollectionNames());
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
