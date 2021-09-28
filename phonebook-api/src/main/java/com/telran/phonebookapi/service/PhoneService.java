package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Phone;
import com.telran.phonebookapi.exception.ContactNotFoundException;
import com.telran.phonebookapi.exception.PhoneNotFoundException;
import com.telran.phonebookapi.repository.ContactRepository;
import com.telran.phonebookapi.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;
    private final ContactRepository contactRepository;

    public PhoneService(PhoneRepository phoneRepository, ContactRepository contactRepository) {
        this.phoneRepository = phoneRepository;
        this.contactRepository = contactRepository;
    }

    public Phone add(String countryCode, String telephoneNumber, boolean favorite, Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("Contact with id " + contactId + " doesn't exist"));
        Phone phone = new Phone(countryCode, telephoneNumber, favorite, contact);
        contact.addPhone(phone);
        return phoneRepository.save(phone);
    }

    public Phone get(Long id) {
        return phoneRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException("Phone with id: " + id + " not found"));
    }

    public void remove(Long id) {
        phoneRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException("Phone with id: " + id + " not found"));
        phoneRepository.deleteById(id);
    }

    public List<Phone> getAll(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("Contact with id " + contactId + " doesn't exist"));
        return contact.getPhones();
    }

    public void edit(Long id, String countryCode, String telephoneNumber, boolean favorite) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException("Phone with id: " + id + " not found"));
        if (countryCode != null)
            phone.setCountryCode(countryCode);
        if (telephoneNumber != null)
            phone.setTelephoneNumber(telephoneNumber);
        phone.setFavorite(favorite);
        phoneRepository.save(phone);
    }

}
