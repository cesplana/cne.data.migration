package com.cne.data.migration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@Configuration
@EnableMongoRepositories(basePackages = {"com.cne.data.migration.dao"})
public class MongoConfiguration {

    //@Value("#{application['spring.data.mongodb.host']}")
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.uri}")
    private String mongoURI;

    /**
     *
     */
    public void displayConfig() {
    }
}
