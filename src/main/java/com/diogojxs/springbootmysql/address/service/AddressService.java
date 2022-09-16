package com.diogojxs.springbootmysql.address.service;

import com.diogojxs.springbootmysql.address.vo.AddressResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

    private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);

    protected final RestTemplate restTemplate;
    final String URL1 = "https://viacep.com.br/ws/";
    final String URL2 = "/json";

    public AddressService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AddressResponseVO getAddressDataFromViaCepAPI(String cep){
        LOG.info("m=getAddressDataFromViaCepAPI, message:Trying to recover address by CEP from ViaCep API, cep={}", cep);
            ResponseEntity<AddressResponseVO> response = restTemplate.getForEntity(URL1 + cep + URL2, AddressResponseVO.class);
            LOG.info("m=getAddressDataFromViaCepAPI, message:Successfully recovered CEP");
            return response.getBody();
    }
}
