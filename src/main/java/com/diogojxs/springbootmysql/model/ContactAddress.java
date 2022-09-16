package com.diogojxs.springbootmysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contact_address")
public class ContactAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address", nullable = false)
    private Long id;

    @Column(name = "postal_area_code", nullable = false, length = 8)
    private String postalAreaCode;

    @Column(name = "address_name", length = 160)
    private String address;

    @Column(name = "address_number", length = 20)
    private String addressNumber;

    @Column(name = "address_complement", length = 40)
    private String addressComplement;

    @Column(name = "district_name", length = 80)
    private String districtName;

    @Column(name = "city", length = 80)
    private String city;

    @Column(name = "federation_unit", length = 80)
    private String federationUnit;
}
