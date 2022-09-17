package com.diogojxs.springbootmysql.address.facade;

import com.diogojxs.springbootmysql.address.service.AddressService;
import com.diogojxs.springbootmysql.vo.ContactAddressVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AddressFacade {

    private static final Logger LOG = LoggerFactory.getLogger(AddressFacade.class);

    private final AddressService addressService;

    public AddressFacade(AddressService addressService){
        this.addressService = addressService;
    }

    public ContactAddressVO getAddressData(String postalCodeArea){
        LOG.info("m=getAddressData, message= recovering address data, postalAreaCode={}", postalCodeArea);
        return addressService.getAddressDataFromViaCepAPI(postalCodeArea);
    }
}
