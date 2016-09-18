package com.viifly.wba.service;


import com.viifly.wba.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ServiceManager {
    private static final Logger logger = LoggerFactory.getLogger(ServiceManager.class);

    private Configuration configuration;
    private MongoService mongoService;

    // @Inject
    private CounterService counterService;

    private ServiceManager() {
        initServices();
    }

    public void initServices() {
        configuration = new Configuration();

        try {
            configuration.load();
        } catch (IOException e) {
            logger.error("error when init configuration", e);
        }

        mongoService = new MongoService();
        mongoService.init(configuration);

        counterService = new CounterServiceImpl();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public MongoService getMongoService() {
        return mongoService;
    }

    public CounterService getCounterService() { return counterService; }

    public static class ServiceHolder{
        static ServiceManager instance = new ServiceManager();
    }

    public static ServiceManager getInstance() {
        return ServiceHolder.instance;
        // Injector injector = Guice.createInjector(new HeroModule());
    }
}
