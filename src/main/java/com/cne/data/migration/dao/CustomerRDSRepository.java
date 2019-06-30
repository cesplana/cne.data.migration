package com.cne.data.migration.dao;

import com.cne.data.migration.entity.pojo.CustomerPojo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRDSRepository extends JpaRepository<CustomerPojo, String> {
}
