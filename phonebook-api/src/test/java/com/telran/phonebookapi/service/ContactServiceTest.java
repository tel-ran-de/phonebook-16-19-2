package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.exception.ContactException;
import com.telran.phonebookapi.repository.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

    @InjectMocks
    ContactService contactService;
    @Mock
    ContactRepository contactRepository;

    @Test
    public void testGetAllContacts() {
        contactService.getAll();
        Mockito.verify(contactRepository, times(1)).findAll();
    }

    @Test
    public void getContactByIdFailedTest() {
        Assertions.assertThrows(ContactException.class, () -> {
            contactService.get(12L);
        });
    }

    @Test
    public void getContactByIdTest() {
        Contact contact = new Contact("Ivanov", "Ivan", 22, true, Group.FAMILY);
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        contactService.get(1L);
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    public void deleteContactByIdTest() {
        contactService.remove(1L);
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    public void addContactTest() {
        Contact contact = new Contact("Ivanov", "Ivan", 22, true, Group.FAMILY);
        contactService.add(contact);
        verify(contactRepository, times(1)).save(contact);

    }
}

