package com.diogojxs.springbootmysql.address.controller;

import com.diogojxs.springbootmysql.address.facade.AddressFacade;
import com.diogojxs.springbootmysql.vo.ContactAddressVO;
import com.sun.istack.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressFacade addressFacade;

    public AddressController(AddressFacade addressFacade) {
        this.addressFacade = addressFacade;
    }

    @GetMapping(value = "/{postalAreaCode}")
    public ContactAddressVO getAddress(@PathVariable @NotNull String postalAreaCode){
        return addressFacade.getAddressData(postalAreaCode);
    }
}
