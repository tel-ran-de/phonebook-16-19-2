package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.CreatePhoneResponse;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.entity.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PhoneMapperTest {
    @InjectMocks
    PhoneMapper mapper;
    @Test
    public void phoneToRegisterPhoneResponse() {
        Contact contact = new Contact("Ivanov", "Ivan", 22, true, Group.FAMILY);
        Phone phone = new Phone("123", "123456", true, contact);
        CreatePhoneResponse createPhoneResponse = mapper.phoneToRegisterPhoneResponse(phone);
        assertEquals(phone.getCountryCode(), createPhoneResponse.getCountryCode());
        assertEquals(phone.getTelephoneNumber(), createPhoneResponse.getTelephoneNumber());
    }


}
