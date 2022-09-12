package com.diogojxs.springbootmysql.service;

import com.diogojxs.springbootmysql.model.Contact;
import com.diogojxs.springbootmysql.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //Finds a contact by ID
    public Optional<Contact> findContactById(Long contactId) {
        return contactRepository.findById(contactId);
    }

    // Creates a contact
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // Updates a contact
    public Contact updateContact(Contact contact, Long contactId) {
        return findContactById(contactId).map(contactUpdated -> {
            contactUpdated.setName(contact.getName());
            contactUpdated.setEmail(contact.getEmail());
            contactUpdated.setPhone(contact.getPhone());
            return contactRepository.save(contactUpdated);
        }).orElse(null);
    }

    // Deletes a contact
    public void deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
    }
}
