package com.cne.data.migration.config;

import com.cne.data.migration.entity.collection.Customer;
import com.cne.data.migration.entity.pojo.CustomerPojo;
import com.cne.data.migration.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class DataLoaderCLRunner implements CommandLineRunner {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.uri}")
    private String mongoURI;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    private MongoConfiguration mongoConfiguration;
    private CustomerService customerService;

    @Autowired
    public DataLoaderCLRunner(MongoConfiguration mongoConfiguration, CustomerService customerService) {
        this.mongoConfiguration = mongoConfiguration;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Executing CMSv2 Data Migration...");
        displayDBConnection();

        mongoConfiguration.displayConfig();

        Set<CustomerPojo> customerPojoSet = customerService.extractData();
        Long dataExtracted = (long) customerPojoSet.size();
        Set<Customer> customerSet = customerService.transformData(customerPojoSet);
        Long dataImportedCount = customerService.loadData(customerSet);

        log.info("================ RESPONSE DETAILS ====================");
        log.info("Number of records retrieved from source DB: {}", dataExtracted);
        log.info("Number of records imported to DB: {}", dataImportedCount);
        log.info("Number of failed: {}", (dataExtracted - dataImportedCount));
        log.info("======================================================");
    }

    private void displayDBConnection() {
        log.info("========= Source DB ==============");
        log.info("Datasource  URL: {}", datasourceUrl);
        log.info("==================================");
        log.info("========= Destination DB =========");
        log.info("Mongo Host: {}", mongoHost);
        log.info("Mongo URI: {}", mongoURI);
        log.info("===================================");
    }
}
