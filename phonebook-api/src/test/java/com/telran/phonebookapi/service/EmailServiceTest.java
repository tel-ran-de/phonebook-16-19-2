package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.exception.ContactNotFoundException;
import com.telran.phonebookapi.exception.EmailNotFoundException;
import com.telran.phonebookapi.repository.ContactRepository;
import com.telran.phonebookapi.repository.EmailRepository;
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
public class EmailServiceTest {
    @InjectMocks
    EmailService emailService;
    @Mock
    EmailRepository emailRepository;
    @Mock
    Contact contact;
    @Mock
    ContactRepository contactRepository;

    @Test
    public void testGetAllEmails() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        Email email = new Email("test@test.com", false, contact);
        ArrayList<Email> emails = new ArrayList<>(Arrays.asList(email, email));
        when(emailService.getAll(1L)).thenReturn(emails);
        List<Email> emailList = (List<Email>) emailService.getAll(1L);
        assertEquals(emailList.size(), 2);

    }

    @Test
    public void testGetAllEmail_emptyList() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        List<Email> emailList = (List<Email>) emailService.getAll(1L);
        assertEquals(emailList.size(), 0);
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllEmails_contactNOtFound() {
        Exception exception = assertThrows(ContactNotFoundException.class, () -> emailService.getAll(1L));
        assertNotNull(exception);
        assertEquals("Contact with id 1 doesn't exist", exception.getMessage());

    }

    @Test
    public void getEmailById() {
        Email email = new Email("test@test.com", false, contact);
        when(emailRepository.findById(1L)).thenReturn(Optional.of(email));

        emailService.get(1L);
        verify(emailRepository, times(1)).findById(1L);
    }

    @Test
    public void getEmailByIdNegative() {
        Exception exception = assertThrows(EmailNotFoundException.class, () -> emailService.get(3L));
        assertNotNull(exception);
        assertEquals("Email with id: 3 not found", exception.getMessage());
    }

    @Test
    public void deleteEmailById() {
        Email email = new Email("test@test.com", false, contact);
        when(emailRepository.findById(123L)).thenReturn(Optional.of(email));
        emailService.remove(123L);
        verify(emailRepository, times(1)).deleteById(123L);
    }

    @Test
    public void deleteEmailByIdNegative() {
        Exception exception = assertThrows(EmailNotFoundException.class, () -> emailService.get(123L));
        assertNotNull(exception);
        assertEquals("Email with id: 123 not found", exception.getMessage());
        verify(emailRepository, never()).deleteById(123L);
    }

    @Test
    public void addEmail() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        Email email = new Email("test@test.com", false, contact);
        emailService.add("test@test.com", false, 1L);
        verify(emailRepository, times(1)).save(email);
    }

    @Test
    public void editEmail() {
        Email email = new Email("test@test.com", false, contact);
        when(emailRepository.findById(123L)).thenReturn(Optional.of(email));

        emailService.edit(123L, "test@test.com", true);

        verify(emailRepository, times(1)).findById(123L);
        verify(emailRepository)
                .save(argThat(argument -> argument.getEmail().equals("test@test.com")
                        && argument.isFavorite()));
    }

    @Test
    public void editEmail_EmailNotFound() {
        Exception exception = assertThrows(
                EmailNotFoundException.class, () -> emailService
                        .edit(123L, "test@test.com", true));
        assertNotNull(exception);
        assertEquals("Email with id: 123 not found", exception.getMessage());
        verify(emailRepository, never()).save(any());
    }
}
