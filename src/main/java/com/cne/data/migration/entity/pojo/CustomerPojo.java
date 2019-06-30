package com.cne.data.migration.entity.pojo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerPojo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUSTOMERID")
    private String customerId;

    @Column(name = "COMPANYNAME")
    private String companyName;

    @Column(name = "CONTACTNAME")
    private String contactName;

    @Column(name = "CONTACTTITLE")
    private String contactTitle;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;
}