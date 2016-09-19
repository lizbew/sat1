package com.viifly.wba.service;

import java.util.List;
import java.util.Map;

/**
 * Created on 2016/9/19.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

public interface MongoService {
    void saveMap(String collectionName, Map<String, String> data);
    List<String> listDatabases();
    List<String> listCollections(String database);
}
