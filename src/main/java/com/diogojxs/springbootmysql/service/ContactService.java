package com.diogojxs.springbootmysql.service;

import com.diogojxs.springbootmysql.models.Contact;
import com.diogojxs.springbootmysql.repository.ContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ContactService {

    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Return all contacts
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    // Finds a contact by ID
    public ResponseEntity<Contact> findById(@PathVariable long id){
        return contactRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // Creates a contact
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // Updates a contact
    public ResponseEntity<Contact> updateContact(@PathVariable("id") long id,
                                                 @RequestBody Contact contact) {
        return contactRepository.findById(id)
                .map(record -> {
                    record.setName(contact.getName());
                    record.setEmail(contact.getEmail());
                    record.setPhone(contact.getPhone());
                    Contact updated = contactRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Deletes a contact
    public ResponseEntity <?> deleteContact(@PathVariable long id) {
        return contactRepository.findById(id)
                .map(record -> {
                    contactRepository.deleteById(id);
                    return ResponseEntity.ok("Contact successfully deleted");
                }).orElse(ResponseEntity.notFound().build());
    }
}
