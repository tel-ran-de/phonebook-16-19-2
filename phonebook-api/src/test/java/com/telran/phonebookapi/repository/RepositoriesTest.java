package com.telran.phonebookapi.repository;

import com.telran.phonebookapi.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RepositoriesTest {
    @Autowired
    TestEntityManager entityManager;
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
        //given
        //no contacts in DB

        //when
        //adding new contact to DB
        Contact contact = new Contact("Ivan", "Petrov", 34, false, Group.FAMILY);
        var savedContactId = contactRepository.save(contact).getId();
        flushAndClear();

        //then
        //read the contact from DB and assure that it exists
        var savedContactOptional = contactRepository.findById(savedContactId);
        assertTrue(savedContactOptional.isPresent());
    }

    @Test
    public void addEmailsToContactTest() {
        //given
        //contact in database
        var contactId = entityManager.persist(new Contact()).getId();
        flushAndClear();

        //when
        //adding 2 emails to the contact in DB
        var savedContact = contactRepository.findById(contactId).orElseThrow();
        Email email0 = new Email("petr@test.com", true, savedContact);
        Email email1 = new Email("misha@test.com", false, savedContact);
        emailRepository.save(email0);
        emailRepository.save(email1);
        flushAndClear();

        //then
        //read the contact from DB and assure that it has 2 emails
        savedContact = contactRepository.findById(contactId).orElseThrow();
        assertEquals(2, (long) savedContact.getEmails().size());
    }

    @Test
    public void deleteEmailFromContactTest() {
        //given
        //contact in database with 2 emails
        var contact = entityManager.persist(new Contact());
        entityManager.persist(new Email("petr@test.com", true, contact));
        entityManager.persist(new Email("misha@test.com", false, contact));
        flushAndClear();

        //when
        //deleting one email from contact
        var persistedContact = contactRepository.findById(contact.getId()).orElseThrow();
        var removedEmail = persistedContact.getEmails().remove(0);
        emailRepository.delete(removedEmail);
        contactRepository.save(persistedContact);
        flushAndClear();

        //then
        //read the contact from DB and assure that it has 1 email
        persistedContact = contactRepository.findById(contact.getId()).orElseThrow();
        assertEquals(1, (long) persistedContact.getEmails().size());
    }

    @Test
    public void updateEmailFromContactTest() {
        //given
        //contact in database with 1 email
        var contact = entityManager.persist(new Contact());
        entityManager.persist(new Email("petr@test.com", true, contact));
        flushAndClear();

        //when
        //updating one email from contact
        var persistedContact = contactRepository.findById(contact.getId()).orElseThrow();
        persistedContact.getEmails().get(0).setEmail("petr222@test.com");
        contactRepository.save(persistedContact);
        flushAndClear();

        //then
        //read the contact from DB and assure that it was changed
        persistedContact = contactRepository.findById(contact.getId()).orElseThrow();
        assertEquals("petr222@test.com", persistedContact.getEmails().get(0).getEmail());
    }

    @Test
    public void addPhoneToContactTest() {
        var contactId = entityManager.persist(new Contact()).getId();
        flushAndClear();

        var savedContact = contactRepository.findById(contactId).orElseThrow();
        Phone phone = new Phone("+7", "1234567", true, savedContact);
        Phone phone2 = new Phone("+8", "6665544", false, savedContact);
        Phone phone3 = new Phone("+9", "3336622", false, savedContact);
        phoneRepository.save(phone);
        phoneRepository.save(phone2);
        phoneRepository.save(phone3);
        flushAndClear();

        savedContact = contactRepository.findById(contactId).orElseThrow();
        assertEquals(3, savedContact.getPhones().size());
    }

    @Test
    public void deletePhoneFromContactTest() {
        var contact = entityManager.persist(new Contact());
        entityManager.persist(new Phone("+7", "1234567", true, contact));
        entityManager.persist(new Phone("+8", "6665544", false, contact));
        entityManager.persist(new Phone("+9", "3336622", false, contact));
        flushAndClear();

        var persistedContact = contactRepository.findById(contact.getId()).orElseThrow();
        var removePhone = persistedContact.getPhones().remove(0);
        phoneRepository.delete(removePhone);
        contactRepository.save(persistedContact);
        assertEquals(2, persistedContact.getPhones().size());
    }


    @Test
    public void addAddressToContactTest() {
        var contactId = entityManager.persist(new Contact()).getId();
        flushAndClear();

        var savedContact = contactRepository.findById(contactId).orElseThrow();

        Address address = new Address("Germany", "Berlin", "123123", "Some street", "11S", true, savedContact);
        Address address2 = new Address("France", "Paris", "646464", "Some other street", "54", false, savedContact);
        addressRepository.save(address);
        addressRepository.save(address2);
        flushAndClear();

        savedContact = contactRepository.findById(contactId).orElseThrow();
        assertEquals(2, savedContact.getAddresses().size());
    }

    @Test
    public void deleteAddressFromContactTest() {
        var contact = entityManager.persist(new Contact());
        entityManager.persist(new Address("Germany", "Berlin", "123123", "Some street", "11S", true, contact));
        entityManager.persist(new Address("France", "Paris", "646464", "Some other street", "54", false, contact));
        flushAndClear();

        var persistedContact = contactRepository.findById(contact.getId()).orElseThrow();
        var removeAddress = persistedContact.getAddresses().remove(0);
        addressRepository.delete(removeAddress);
        contactRepository.save(persistedContact);
        assertEquals(1, persistedContact.getAddresses().size());
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}