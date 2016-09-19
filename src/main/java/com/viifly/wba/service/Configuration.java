package com.viifly.wba.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class Configuration {
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    public static final String PROPS_FILE = "/config.properties";
    private Properties props;

    @PostConstruct
    public void load() throws IOException {
        props = new Properties();

        //FileInputStream fin = new FileInputStream(PROPS_FILE);
        //System.out.println(this.getClass().getResource(PROPS_FILE));
        InputStream fin = this.getClass().getResourceAsStream(PROPS_FILE);
        if (fin != null) {
            props.load(fin);
            fin.close();
        } else {
            logger.error("Failed to load config " + PROPS_FILE);
        }

    }

    public String getValue(String key) {
        return props.getProperty(key);
    }

}
