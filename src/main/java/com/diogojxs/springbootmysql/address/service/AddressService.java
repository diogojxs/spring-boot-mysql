package com.diogojxs.springbootmysql.address.service;

import com.diogojxs.springbootmysql.address.vo.AddressResponseVO;
import com.diogojxs.springbootmysql.vo.ContactAddressVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class AddressService {

    private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);

    protected final RestTemplate restTemplate;
    final String URL1 = "https://viacep.com.br/ws/";
    final String URL2 = "/json";

    public AddressService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ContactAddressVO getAddressDataFromViaCepAPI(String postalAreaCode){
        LOG.info("m=getAddressDataFromViaCepAPI, message:Trying to recover address by CEP from ViaCep API, cep={}", postalAreaCode);
            ResponseEntity<AddressResponseVO> response = restTemplate.getForEntity(URL1 + postalAreaCode + URL2, AddressResponseVO.class);
            LOG.info("m=getAddressDataFromViaCepAPI, message:Successfully recovered CEP");
            response.getBody();

            ContactAddressVO contactAddress = new ContactAddressVO();
            contactAddress.setPostalAreaCode(Objects.requireNonNull(response.getBody()).getCep());
            contactAddress.setAddress(response.getBody().getLogradouro());
            contactAddress.setDistrictName(response.getBody().getBairro());
            contactAddress.setCity(response.getBody().getLocalidade());
            contactAddress.setFederationUnit(response.getBody().getUf());

            return contactAddress;
    }
}
