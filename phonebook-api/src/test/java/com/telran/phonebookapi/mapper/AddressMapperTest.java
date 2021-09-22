package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.AddressDto;
import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AddressMapperTest {
    @InjectMocks
    AddressMapper mapper;

    @Test
    public void AddressMapperTest_toDto(){
        Contact contact = new Contact("Ivanov", "Ivan", 22, true, Group.FAMILY);
        Address address = new Address("Germany", "Karlsruhe", "77137", "Morgenst.",
                "11", true, contact);
        AddressDto addressDto= mapper.toDto(address);
        assertEquals(address.getCountry(),addressDto.country);
        assertEquals(address.getCity(),addressDto.city);
        assertEquals(address.getIndex(), addressDto.index);
        assertEquals(address.getStreet(), addressDto.street);
        assertEquals(address.getHomeNr(), addressDto.homeNr);
        assertEquals(address.isFavorite(), addressDto.isFavorite);
    }
}
