package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.entity.Phone;
import com.telran.phonebookapi.exception.PhoneNotFoundException;
import com.telran.phonebookapi.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    public void testGetAllPhones() {
        phoneService.getAll();
        verify(phoneRepository, times(1)).findAll();
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
    public void addPhone() {
        phoneService.add("+49", "7778885566", false, contact);
        verify(phoneRepository, times(1)).save(any());
    }

    @Test
    public void editPhone() {
        Phone phone = new Phone("+49", "7778885566", false, contact);
        when(phoneRepository.findById(123L)).thenReturn(Optional.of(phone));

        phoneService.edit(123L, "+7", "9132551670", true);

        verify(phoneRepository, times(1)).findById(123L);
        verify(phoneRepository, times(1))
                .save(argThat(argument -> argument.getCountryCode().equals("+7")
                        && argument.getTelephoneNumber().equals("9132551670")
                        && argument.isFavorite()));
    }
}
