package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.exception.ContactNotFoundException;
import com.telran.phonebookapi.exception.EmailNotFoundException;
import com.telran.phonebookapi.repository.ContactRepository;
import com.telran.phonebookapi.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final EmailRepository emailRepository;
    private final ContactRepository contactRepository;


    public EmailService(EmailRepository emailRepository, ContactRepository contactRepository) {
        this.emailRepository = emailRepository;
        this.contactRepository = contactRepository;
    }

    public Email add(String email, boolean isFavorite, Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("Contact with id " + contactId + " doesn't exist"));
        Email newEmail = new Email(email, isFavorite, contact);
        contact.addEmail(newEmail);
        return emailRepository.save(newEmail);
    }

    public Email get(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new EmailNotFoundException("Email with id: " + id + " not found"));
    }

    public void remove(Long id) {
        emailRepository.findById(id)
                .orElseThrow(() -> new EmailNotFoundException("Email with id: " + id + " not found"));
        emailRepository.deleteById(id);
    }

    public List<Email> getAll(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("Contact with id " + contactId + " doesn't exist"));
        return contact.getEmails();
    }

    public void edit(Long id, String email, boolean isFavorite) {
        Email newEmail = emailRepository.findById(id)
                .orElseThrow(() -> new EmailNotFoundException("Email with id: " + id + " not found"));
        if (email != null)
            newEmail.setEmail(email);
            newEmail.setFavorite(isFavorite);
         emailRepository.save(newEmail);
    }
}
