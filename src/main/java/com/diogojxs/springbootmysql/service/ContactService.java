package com.diogojxs.springbootmysql.service;

import com.diogojxs.springbootmysql.model.Contact;
import com.diogojxs.springbootmysql.repository.ContactRepository;
import com.diogojxs.springbootmysql.vo.ContactAddressVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private static final Logger LOG = LoggerFactory.getLogger(ContactService.class);

    ContactRepository contactRepository;

    protected final RestTemplate restTemplate;

    final String URL = "http://localhost:8080/address/";

    public ContactService(ContactRepository contactRepository, RestTemplate restTemplate) {
        this.contactRepository = contactRepository;
        this.restTemplate = restTemplate;
    }

    // Return all contacts
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    //Finds a contact by ID
    public Optional<Contact> findContactById(Long id) {
        return contactRepository.findById(id);
    }

    // Creates a contact
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public ContactAddressVO getAddressDataByPostalAreaCode(String postalAreaCode){
        LOG.info("m=getAddressDataByPostalAreaCode, message:Trying to recover address by postalAreaCode, postalAreaCode={}", postalAreaCode);
        ResponseEntity<ContactAddressVO> response = restTemplate.getForEntity(URL + postalAreaCode, ContactAddressVO.class);
        LOG.info("m=getAddressDataByPostalAreaCode, message:Successfully recovered Address");
        return response.getBody();
    }

    // Updates a contact
    public Contact updateContact(Contact contact, Long id) {
        return findContactById(id).map(contactUpdated -> {
            contactUpdated.setName(contact.getName());
            contactUpdated.setEmail(contact.getEmail());
            contactUpdated.setPhone(contact.getPhone());
            return contactRepository.save(contactUpdated);
        }).orElse(null);
    }

    // Deletes a contact
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
