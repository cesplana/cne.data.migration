package com.cne.data.migration.entity.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "customers")
@Data
public class Customer {
    @Id
    private String id;
    @Field("customerId")
    private String customerId;
    @Field("companyName")
    @Indexed
    private String companyName;
    @Field("contactName")
    private String contactName;
    @Field("contactTitle")
    private String contactTitle;
    @Field("address")
    private String address;
    @Field("city")
    private String city;
}
