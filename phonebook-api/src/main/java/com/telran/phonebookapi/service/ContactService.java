package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.exception.ContactNotFoundException;
import com.telran.phonebookapi.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact add(String fName, String lName, int age, boolean isFavorite, Group group) {
        return contactRepository.save(new Contact(fName, lName, age, isFavorite, group));
    }

    public Contact get(Long id) {
       return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact with id " + id + " doesn't exist"));
    }

    public void remove (Long id) {
        contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact with id " + id + " doesn't exist"));
        contactRepository.deleteById(id);
    }

    public Iterable<Contact> getAll() {
        return contactRepository.findAll();
    }

    public void editContact(Long id, String fName, String lName, int age, boolean isFavorite, Group group) {
        Contact contact = contactRepository.findById(id).
                orElseThrow(() -> new ContactNotFoundException("Contact with id " + id + " doesn't exist"));
        if(fName != null)
            contact.setFirstName(fName);
        if(lName != null)
        contact.setLastName(lName);
        contact.setAge(age);
        contact.setFavorite(isFavorite);
        contact.setGroup(group);
        contactRepository.save(contact);
    }
}
