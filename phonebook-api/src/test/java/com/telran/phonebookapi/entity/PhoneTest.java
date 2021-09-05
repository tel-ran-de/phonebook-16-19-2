package com.telran.phonebookapi.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PhoneTest {

    @InjectMocks
    Phone phone;

    @Mock
    Contact contact;

    @Test
    public void createPhoneTest() {
        Phone phone = new Phone();
    }
}
