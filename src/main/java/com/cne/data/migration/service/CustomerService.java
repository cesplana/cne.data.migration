package com.cne.data.migration.service;

import com.cne.data.migration.dao.CustomerMongoRepository;
import com.cne.data.migration.dao.CustomerRDSRepository;
import com.cne.data.migration.entity.collection.Customer;
import com.cne.data.migration.entity.pojo.CustomerPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService implements BaseDataLoaderService<CustomerPojo, Customer> {

    private CustomerRDSRepository customerRDSRepository;

    private CustomerMongoRepository customerMongoRepository;

    @Autowired
    public CustomerService(CustomerRDSRepository customerRDSRepository, CustomerMongoRepository customerMongoRepository) {
        this.customerRDSRepository = customerRDSRepository;
        this.customerMongoRepository = customerMongoRepository;
    }

    @Override
    public Set<CustomerPojo> extractData() {
        log.info("Extracting data from source DB...");

        if (null != customerRDSRepository) {
            return StreamUtils.createStreamFromIterator(customerRDSRepository.findAll().iterator()).collect(Collectors.toSet());
        }
        return Collections.EMPTY_SET;
    }

    @Override
    public Set<Customer> transformData(Set<CustomerPojo> customerPojoSet) {
        log.info("Transforming data...");

        if (CollectionUtils.isNotEmpty(customerPojoSet)) {
            Set<Customer> customers = customerPojoSet.stream().parallel().map(customerPojo -> {
                Customer customer = new Customer();

                customer.setCustomerId(customerPojo.getCustomerId());
                customer.setContactTitle(customerPojo.getContactTitle());
                customer.setContactName(customerPojo.getContactName());
                customer.setCompanyName(customerPojo.getCompanyName());

                return customer;
            }).collect(Collectors.toSet());

            return customers;
        }
        return Collections.EMPTY_SET;
    }

    @Override
    @Transactional
    public Long loadData(Set<Customer> documents) {
        log.info("Loading data to destination DB...");

        if(null != customerMongoRepository && CollectionUtils.isNotEmpty(documents)) {
            List<Customer> savedList = customerMongoRepository.saveAll(documents);
            if (CollectionUtils.isNotEmpty(savedList)) {
                return new Long(savedList.size());
            }
        }
        return 0L;
    }
}
