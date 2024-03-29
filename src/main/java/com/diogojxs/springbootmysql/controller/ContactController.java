package com.diogojxs.springbootmysql.controller;

import com.diogojxs.springbootmysql.service.ContactService;
import com.diogojxs.springbootmysql.model.Contact;
import com.diogojxs.springbootmysql.vo.ContactAddressVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
@Api(value = "API REST Contacts")
@CrossOrigin(origins = "*")
public class ContactController {
    private static final Logger logger = Logger.getLogger(ContactController.class);

    @Autowired
    ContactService contactService;

    @GetMapping("/list")
    @ApiOperation(value = "Returns all contacts")
    public List<Contact> findAll() {
        return contactService.findAll();
    }

    @ApiOperation(value = "Finds a contact by ID")
    @GetMapping("contact/{id}")
    public ResponseEntity<Optional<Contact>> findByID(@PathVariable("id") Long id) {
        if (contactService.findContactById(id).isPresent()) {
            return ResponseEntity.ok(contactService.findContactById(id));
        } else {
            logger.error("ID not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/contact")
    @ApiOperation(value = "Creates a contact")
    public Contact create(@RequestBody Contact contact) {

        ContactAddressVO contactAddressVO = contactService.getAddressDataByPostalAreaCode(contact.getContactAddress().getPostalAreaCode());
        contact.getContactAddress().setAddress(contactAddressVO.getAddress());
        contact.getContactAddress().setDistrictName(contactAddressVO.getDistrictName());
        contact.getContactAddress().setCity(contactAddressVO.getCity());
        contact.getContactAddress().setFederationUnit(contactAddressVO.getFederationUnit());

        return contactService.saveContact(contact);
    }

    @PutMapping("/contact/{id}")
    @ApiOperation(value = "Updates a contact")
    public ResponseEntity<Contact> update(@RequestBody Contact contact,
                                          @PathVariable("id") Long id) {
        return ResponseEntity.ok(contactService.updateContact(contact, id));
    }

    @DeleteMapping("/contact/{id}")
    @ApiOperation(value = "Deletes a contact")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            contactService.deleteContact(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
