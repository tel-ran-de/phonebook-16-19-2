package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.exception.AddressNotFoundException;
import com.telran.phonebookapi.repository.AddressRepository;
import com.telran.phonebookapi.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    AddressService addressService;

    @Mock
    ContactRepository contactRepository;

    @Mock
    AddressRepository addressRepository;

    @Mock
    Contact contact;

    @Test
    public void addAddressTest() {
        //Contact contact = new Contact("Ivanov", "Ivan", 25, true, Group.FAMILY);
        Address address = new Address("Germany", "Karlsruhe", "77137", "Morgenst.", "11", true, contact);
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        addressService.add("Germany", "Karlsruhe", "77137", "Morgenst.", "11", true, 1L);
        verify(addressRepository, times(1)).save(address);
    }


    @Test
    public void addContactNotExist() {
        Address address = new Address("Country", "City", "index", "Street", "homeNr", false, contact);
        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                addressService.add(address.getCountry(), address.getCity(), address.getIndex(), address.getStreet(), address.getHomeNr(), address.isFavorite(),
                        address.getContact().getId() + 1));
        verify(contactRepository, times(1)).findById(1L);
        assertEquals("Contact with id " + (contact.getId() + 1) + " doesn't exist", exception.getMessage());

    }

    @Test
    public void editAddressTest() {
        Address address = new Address("Germany", "Karlsruhe", "77137", "Morgenst.", "11", false, contact);
        when(addressRepository.findById(123L)).thenReturn(Optional.of(address));
        addressService.edit(123L, "Russia", "SBP", "12600", "Esenina", "1", true);
        verify(addressRepository, times(1)).findById(123L);
        verify(addressRepository)
                .save(argThat(argument -> argument.getCountry().equals("Russia")
                        && argument.getCity().equals("SBP")
                        && argument.getIndex().equals("12600")
                        && argument.getStreet().equals("Esenina")
                        && argument.getHomeNr().equals("1")
                        && argument.isFavorite()));

    }


    @Test
    public void editAddressOneChange() {
        Address address = new Address("Germany", "Karlsruhe", "77137", "Morgenst.", "11", true, contact);
        when(addressRepository.findById(123L)).thenReturn(Optional.of(address));
        addressService.edit(123L, "Germany", "SBP", "77137", "Morgenst.", "11", true);
        verify(addressRepository, times(1)).findById(123L);
        verify(addressRepository)
                .save(argThat(argument -> argument.getCountry().equals("Germany")
                        && argument.getCity().equals("SBP")
                        && argument.getIndex().equals("77137")
                        && argument.getStreet().equals("Morgenst.")
                        && argument.getHomeNr().equals("11")
                        && argument.isFavorite()));

    }

    @Test
    public void getAddressByIdTest() {
        Address address = new Address("Germany", "Karlsruhe", "77137", "Morgenst.", "11", true, contact);
        when(addressRepository.findById(12L)).thenReturn(Optional.of(address));
        addressService.getById(12L);
        verify(addressRepository, times(1)).findById(12L);

    }

    @Test
    public void getAllTest() {
        when(contactRepository.existsById(1L)).thenReturn(true);
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        Address address = new Address("Germany", "Karlsruhe", "77137", "Morgenst.", "11", true, contact);
        ArrayList<Address> addresses = new ArrayList<>(Arrays.asList(address, address, address, address));

        when(addressService.getAll(1L)).thenReturn(addresses);
        List<Address> addressList = (List<Address>) addressService.getAll(1L);
        assertEquals(addressList.size(), 4);

    }

    @Test
    public void getAllWhenListIsEmptyTest() {
        when(contactRepository.existsById(12L)).thenReturn(true);
        when(contactRepository.findById(12L)).thenReturn(Optional.of(contact));
        List<Address> addressList = (List<Address>) addressService.getAll(12L);
        assertEquals(addressList.size(), 0);
        verify(contactRepository, times(1)).findById(12L);

    }

    @Test
    public void removeByIdTest() {
        Exception exception = assertThrows(AddressNotFoundException.class, () -> addressService.getById(12L));
        assertNotNull(exception);
        assertEquals("Address with id 12 not found", exception.getMessage());
        verify(addressRepository, never()).deleteById(1L);

    }

}
