package com.telran.phonebookapi.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ContactTest {

    @InjectMocks
    Contact contact;

    @Mock
    Address address;
    @Mock
    Email email;
    @Mock
    Phone phone;

    private Group group;

    @Test
    public void createContactTest() {

    }
}
