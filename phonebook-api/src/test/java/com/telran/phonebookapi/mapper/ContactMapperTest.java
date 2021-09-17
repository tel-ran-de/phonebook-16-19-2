package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.ContactDto;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ContactMapperTest {
    @InjectMocks
    ContactMapper mapper;

    @Test
    public void contactMapperTest_toDto() {
        Contact contact = new Contact("Ivanov", "Ivan", 22, true, Group.FAMILY);
        ContactDto contactDto = mapper.toDto(contact);
        assertEquals(contact.getFirstName(), contactDto.firstName);
        assertEquals(contact.getLastName(), contactDto.lastName);
        assertEquals(contact.getAge(), contactDto.age);
        assertEquals(contact.isFavorite(), contactDto.isFavorite);
        assertEquals(contact.getGroup(), Group.valueOf(contactDto.group));
    }

    @Test
    public void contactMapperTest_fromDto() {
        Contact contact = new Contact("Ivanov", "Ivan", 22, true, Group.FAMILY);
        ContactDto contactDto = mapper.toDto(contact);
        Contact contact2 = mapper.fromDto(contactDto);
        assertEquals(contact.getFirstName(), contact2.getFirstName());
        assertEquals(contact.getLastName(), contact2.getLastName());
        assertEquals(contact.getAge(), contact2.getAge());
        assertEquals(contact.isFavorite(), contact2.isFavorite());
        assertEquals(contact.getGroup(), contact2.getGroup());
    }
}
