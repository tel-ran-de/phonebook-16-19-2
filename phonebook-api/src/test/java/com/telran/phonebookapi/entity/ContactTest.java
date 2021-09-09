package com.telran.phonebookapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ContactTest {

    @Test
    public void createContactTest() {
        Contact contact = new Contact("fName", "lName", 18, true, Group.FAMILY);
        assertNotNull(contact);
    }

    @Test
    public void exceptionContactTest() {
        assertThrows(NullPointerException.class, () -> new Contact(null, "lName", 18, true, Group.FAMILY));
        assertThrows(NullPointerException.class, () -> new Contact("fName", null, 18, true, Group.FAMILY));
        assertThrows(NullPointerException.class, () -> new Contact("fName", "lName", 18, true, null));
        assertThrows(NullPointerException.class, () -> new Contact(null, null, 18, true, null));
    }

    @Test
    public void gettersAndSettersContactTest() {
        Contact contact = new Contact("fName", "lName", 18, true, Group.FAMILY);

        assertEquals("fName", contact.getFirstName());
        contact.setFirstName("Vania");
        assertEquals("Vania", contact.getFirstName());

        assertEquals("lName", contact.getLastName());
        contact.setLastName("Ivanov");
        assertEquals("Ivanov", contact.getLastName());

        assertEquals(18, contact.getAge());
        contact.setAge(19);
        assertEquals(19, contact.getAge());

        assertTrue(contact.isFavorite());
        contact.setFavorite(false);
        assertFalse(contact.isFavorite());

        assertEquals(Group.FAMILY, contact.getGroup());
        contact.setGroup(Group.FRIENDS);
        assertEquals(Group.FRIENDS, contact.getGroup());
    }

    @Test
    public void methodsContactTest() {
        Contact contact = new Contact("fName", "lName", 18, true, Group.FAMILY);

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
