package com.diogojxs.springbootmysql.address.facade;

import com.diogojxs.springbootmysql.address.service.AddressService;
import com.diogojxs.springbootmysql.address.vo.AddressResponseVO;
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

    public AddressResponseVO getAddressData(String cep){
        LOG.info("m=getAddressData, message= recovering address data, cep={}", cep);
        return addressService.getAddressDataFromViaCepAPI(cep);
    }
}
