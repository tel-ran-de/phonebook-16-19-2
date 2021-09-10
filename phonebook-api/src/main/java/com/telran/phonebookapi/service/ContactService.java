package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.exception.ContactException;
import com.telran.phonebookapi.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact add(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact get(Long id) {
       return contactRepository.findById(id)
                .orElseThrow(() -> new ContactException("Contact with id " + id + " doesn't exist"));
    }

    public void remove (Long id) {
        contactRepository.deleteById(id);
    }

    public Iterable<Contact> getAll() {
        return contactRepository.findAll();
    }
}
