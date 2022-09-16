package com.diogojxs.springbootmysql.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContactAddressVO {
    private String postalAreaCode;
    private String address;
    private String addressNumber;
    private String addressComplement;
    private String districtName;
    private String city;
    private String federationUnit;
}
