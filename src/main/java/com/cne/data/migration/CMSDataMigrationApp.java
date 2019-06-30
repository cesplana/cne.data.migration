package com.cne.data.migration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CMSDataMigrationApp {

    public static void main(String[] args) {
        SpringApplication.run(CMSDataMigrationApp.class, args);
    }
}
