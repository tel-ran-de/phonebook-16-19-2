package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Phone;
import com.telran.phonebookapi.exception.PhoneNotFoundException;
import com.telran.phonebookapi.repository.ContactRepository;
import com.telran.phonebookapi.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {
    @InjectMocks
    PhoneService phoneService;

    @Mock
    PhoneRepository phoneRepository;
    @Mock
    Contact contact;
    @Mock
    ContactRepository contactRepository;

    @Test
    public void testGetAllPhones() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        Phone phone = new Phone("+49", "7778885566", false, contact);
        ArrayList<Phone> phones = new ArrayList<>(Arrays.asList(phone, phone));
        when(phoneService.getAll(1L)).thenReturn(phones);
        List<Phone> phoneList = (List<Phone>) phoneService.getAll(1L);
        assertEquals(phoneList.size(), 2);

    }

    @Test
    public void testGetAllPhones_emptyList() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        List<Phone> phoneList = (List<Phone>) phoneService.getAll(1L);
        assertEquals(phoneList.size(), 0);
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllPhones_contactNOtFound() {
        Exception exception = assertThrows(ContactNotFoundException.class, () -> phoneService.getAll(1L));
        assertNotNull(exception);
        assertEquals("Contact with id 1 doesn't exist", exception.getMessage());

    }

    @Test
    public void getPhoneById() {
        Phone phone = new Phone("+49", "7778885566", false, contact);
        when(phoneRepository.findById(1L)).thenReturn(Optional.of(phone));

        phoneService.get(1L);
        verify(phoneRepository, times(1)).findById(1L);
    }

    @Test
    public void getPhoneByIdNegative() {
        Exception exception = assertThrows(PhoneNotFoundException.class, () -> phoneService.get(3L));
        assertNotNull(exception);
        assertEquals("Phone with id: 3 not found", exception.getMessage());
    }

    @Test
    public void deletePhoneById() {
        Phone phone = new Phone("+49", "7778885566", false, contact);
        when(phoneRepository.findById(123L)).thenReturn(Optional.of(phone));
        phoneService.remove(123L);
        verify(phoneRepository, times(1)).deleteById(123L);
    }

    @Test
    public void deletePhoneByIdNegative() {
        try {
            doThrow(new PhoneNotFoundException("Phone with id: 123 not found")).when(phoneService).remove(123L);
            verify(phoneService).remove(123L);
        } catch (Exception ignored) {
        }
        Exception exception = assertThrows(PhoneNotFoundException.class, () -> phoneService.get(123L));
        assertNotNull(exception);
        assertEquals("Phone with id: 123 not found", exception.getMessage());
        verify(phoneRepository, never()).deleteById(123L);
    }

    @Test
    public void addPhone() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        Phone phone = new Phone("+49", "7778885566", false, contact);
        phoneService.add("+49", "7778885566", false, 1L);
        verify(phoneRepository, times(1)).save(phone);
    }

    @Test
    public void addPhoneNegative() {
        doThrow(new ArithmeticException("Contact not found")).when(contactRepository).findById(1L);
        Phone phone = new Phone("+49", "7778885566", false, contact);
        try {
            phoneService.add("+49", "7778885566", false, 1L);
            verify(contactRepository).findById(1L);
        } catch (Exception e) {
            verify(phoneRepository, never()).save(phone);
        }
    }

    @Test
    public void editPhone() {
        Phone phone = new Phone("+49", "7778885566", false, contact);
        when(phoneRepository.findById(123L)).thenReturn(Optional.of(phone));

        phoneService.edit(123L, "+7", "9132551670", true);

        verify(phoneRepository, times(1)).findById(123L);
        verify(phoneRepository)
                .save(argThat(argument -> argument.getCountryCode().equals("+7")
                        && argument.getTelephoneNumber().equals("9132551670")
                        && argument.isFavorite()));
    }

    @Test
    public void editPhone_PhoneNotFound() {
        Exception exception = assertThrows(
                PhoneNotFoundException.class, () -> phoneService
                        .edit(123L, "+7", "9132551670", true));
        assertNotNull(exception);
        assertEquals("Phone with id: 123 not found", exception.getMessage());
        verify(phoneRepository, never()).save(any());
    }
}
