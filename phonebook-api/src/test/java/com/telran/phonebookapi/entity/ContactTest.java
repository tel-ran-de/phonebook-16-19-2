package com.telran.phonebookapi.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;



public class ContactTest {
    Contact contact;
    private static Group group;

    @Test
    public void createContactTest() {
        Contact contact = new Contact("fName", "lName", 18, true, group.Family);
    }
}
