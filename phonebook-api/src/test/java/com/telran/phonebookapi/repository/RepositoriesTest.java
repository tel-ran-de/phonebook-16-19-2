package com.telran.phonebookapi.repository;

import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.entity.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RepositoriesTest {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    AddressRepository addressRepository;

    @Test
    public void createContactTest() {
        Contact contact = new Contact();
        contact.setFirstName("Ivan");
        contact.setLastName("Petrov");
        contact.setAge(34);
        var savedContactId = contactRepository.save(contact).getId();
        var savedContactOptional = contactRepository.findById(savedContactId);
        assertTrue(savedContactOptional.isPresent());
    }

    @Test
    public void addAndDeleteEmailsToContactTest() {
        Contact contact = new Contact();

        Email email0 = new Email();
        email0.setEmail("petr@test.com");
        emailRepository.save(email0);
        contact.addEmail(email0);

        Email email1 = new Email();
        email1.setEmail("misha@test.com");
        emailRepository.save(email1);
        contact.addEmail(email1);

        var savedContactId = contactRepository.save(contact).getId();
        var savedContact = contactRepository.findById(savedContactId).orElseThrow();
        assertEquals(2, (long) savedContact.getEmails().size());

        savedContact.getEmails().remove(0);
        contactRepository.save(savedContact);
        savedContact = contactRepository.findById(savedContactId).orElseThrow();
        assertEquals(1, (long) savedContact.getEmails().size());

        email1.setEmail("misha222@test.com");
        emailRepository.save(email1);
        savedContact = contactRepository.findById(savedContactId).orElseThrow();
        assertEquals("misha222@test.com", savedContact.getEmails().get(0).getEmail());
    }

    @Test
    public void addAndDeletePhonesToContactTest() {
        Contact contact = new Contact();

        Phone phone = new Phone();
        phone.setTelephoneNumber("1234567");
        phoneRepository.save(phone);
        contact.addPhone(phone);

        Phone phone2 = new Phone();
        phone2.setTelephoneNumber("2223344");
        phoneRepository.save(phone2);
        contact.addPhone(phone2);

        Phone phone3 = new Phone();
        phone3.setTelephoneNumber("4445566");
        phoneRepository.save(phone3);
        contact.addPhone(phone3);

        var savedContactId = contactRepository.save(contact).getId();
        var savedContact = contactRepository.findById(savedContactId).orElseThrow();
        assertEquals(3, savedContact.getPhones().size());

        savedContact.getPhones().remove(0);
        contactRepository.save(savedContact);
        savedContact = contactRepository.findById(savedContactId).orElseThrow();
        assertEquals(2, savedContact.getPhones().size());
    }

    @Test
    public void addAndDeleteAddressesToContactTest() {
        Contact contact = new Contact();

        Address address = new Address();
        address.setIndex("123123");
        address.setCity("London");
        address.setCountry("GB");
        address.setHomeNr("11");
        addressRepository.save(address);
        contact.addAddress(address);

        Address address2 = new Address();
        address2.setIndex("555444");
        address2.setCity("Berlin");
        address2.setCountry("Germany");
        address2.setHomeNr("22");
        addressRepository.save(address2);
        contact.addAddress(address2);

        var savedContactId = contactRepository.save(contact).getId();
        var savedContact = contactRepository.findById(savedContactId).orElseThrow();
        assertEquals(2, savedContact.getAddresses().size());

        savedContact.getAddresses().remove(0);
        contactRepository.save(savedContact);
        savedContact = contactRepository.findById(savedContactId).orElseThrow();
        assertEquals(1, savedContact.getAddresses().size());
    }
}