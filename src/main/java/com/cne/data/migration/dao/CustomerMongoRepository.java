package com.cne.data.migration.dao;

import com.cne.data.migration.entity.collection.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerMongoRepository extends MongoRepository<Customer, String> {
}
