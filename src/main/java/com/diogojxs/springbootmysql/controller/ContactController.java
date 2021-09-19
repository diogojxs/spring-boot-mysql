package com.diogojxs.springbootmysql.controller;

import com.diogojxs.springbootmysql.service.ContactService;
import com.diogojxs.springbootmysql.models.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@Api(value = "API REST Contacts")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/list")
    @ApiOperation(value = "Returns all contacts")
    public List<Contact> findAll() {
        return contactService.findAll();
    }

    @GetMapping(path = {"/contact/{id}"})
    @ApiOperation(value = "Finds a contact by ID")
    public ResponseEntity<Contact> findById(@PathVariable long id) {
        return contactService.findById(id);
    }

    @PostMapping("/contact")
    @ApiOperation(value = "Creates a contact")
    public Contact create(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }

    @PutMapping(value="/contact/{id}")
    @ApiOperation(value = "Updates a contact")
    public ResponseEntity<Contact> update(@PathVariable("id") long id,
                                          @RequestBody Contact contact) {
        return contactService.updateContact(id, contact);
    }

    @DeleteMapping(path ={"/contact/{id}"})
    @ApiOperation(value = "Deletes a contact")
    public ResponseEntity <?> delete(@PathVariable long id) {
        return contactService.deleteContact(id);
    }
}
