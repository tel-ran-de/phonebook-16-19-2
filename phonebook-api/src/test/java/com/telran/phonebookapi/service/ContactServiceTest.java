package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.exception.ContactNotFoundException;
import com.telran.phonebookapi.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        Exception exception = assertThrows(ContactNotFoundException.class, () -> contactService.get(12L));
        assertNotNull(exception);
        assertEquals("Contact with id 12 doesn't exist", exception.getMessage());

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
        Contact contact = new Contact("Ivanov", "Ivan", 22, true, Group.FAMILY);
        when(contactRepository.findById(123L)).thenReturn(Optional.of(contact));
        contactService.remove(123L);
        verify(contactRepository, times(1)).deleteById(123L);
    }

    @Test
    public void addContactTest() {
        contactService.add("Ivanov", "Ivan", 22, true, Group.FAMILY);
        verify(contactRepository, times(1)).save(any());
    }

    @Test
    public void editContact() {
        Contact contact = new Contact("Ivanov", "Ivan", 22, true, Group.FAMILY);

        when(contactRepository.findById(123L)).thenReturn(Optional.of(contact));

        contactService.editContact(123L, "Misha", "Petrov", 22, true, Group.FAMILY);

        verify(contactRepository, times(1)).findById(123L);
        verify(contactRepository, times(1))
                .save(argThat(argument -> argument.getFirstName().equals("Misha")
                        && argument.getLastName().equals("Petrov")
                        && argument.getAge() == 22
                        && argument.isFavorite()
                        && argument.getGroup() == Group.FAMILY));
    }

    @Test
    public void editContact_SomeFieldsAreNull() {
        Contact contact = new Contact("Ivan", "Ivanov", 22, true, Group.FAMILY);
        when(contactRepository.findById(123L)).thenReturn(Optional.of(contact));
        contactService.editContact(123L, null, null, 22, true, Group.FAMILY);

        verify(contactRepository, times(1)).findById(123L);
        verify(contactRepository, times(1))
                .save(argThat(argument -> argument.getFirstName().equals("Ivan")
                        && argument.getLastName().equals("Ivanov")
                        && argument.getAge() == 22
                        && argument.isFavorite()
                        && argument.getGroup() == Group.FAMILY));
    }
}

