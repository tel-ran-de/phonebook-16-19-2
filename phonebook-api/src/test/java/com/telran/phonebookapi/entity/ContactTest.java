package com.telran.phonebookapi.entity;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.MethodNotAllowedException;

import javax.el.MethodNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


public class ContactTest {
    Contact contact;

    @Test
    public void createContactTest() {
        contact = new Contact("fName", "lName", 18, true, Group.Family);
        assertNotNull(contact);
    }

    @Test
    public void exceptionContactTest() {
        assertThrows(NullPointerException.class, () -> new Contact(null, "lName", 18, true, Group.Family));
        assertThrows(NullPointerException.class, () -> new Contact("fName", null, 18, true, Group.Family));
        assertThrows(NullPointerException.class, () -> new Contact("fName", "lName", 18, true, null));
        assertThrows(NullPointerException.class, () -> new Contact(null, null, 18, true, null));
    }

    @Test
    public void gettersAndSettersContactTest() {
        contact = new Contact("fName", "lName", 18, true, Group.Family);

        assertEquals("fName", contact.getFirsName());
        contact.setFirsName("Vania");
        assertEquals("Vania", contact.getFirsName());

        assertEquals("lName", contact.getLastName());
        contact.setLastName("Ivanov");
        assertEquals("Ivanov", contact.getLastName());

        assertEquals(18, contact.getAge());
        contact.setAge(19);
        assertEquals(19, contact.getAge());

        assertTrue(contact.isFavorite());
        contact.setFavorite(false);
        assertFalse(contact.isFavorite());

        assertEquals(Group.Family, contact.getGroup());
        contact.setGroup(Group.Friends);
        assertEquals(Group.Friends, contact.getGroup());
    }

    @Test
    public void methodsContactTest() {
        contact = new Contact("fName", "lName", 18, true, Group.Family);


        assertEquals(0, contact.getAddresses().size());
        contact.addAddress(new Address());
        assertEquals(1, contact.getAddresses().size());

        assertEquals(0, contact.getEmails().size());
        contact.addEmail(new Email());
        assertEquals(1, contact.getEmails().size());

        assertEquals(0, contact.getPhones().size());
        contact.addPhone(new Phone());
        assertEquals(1, contact.getPhones().size());

    }
}
