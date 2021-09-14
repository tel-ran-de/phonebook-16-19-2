package com.telran.phonebookapi.repository;

import com.telran.phonebookapi.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddressRepositoryTest {
    @Autowired
    AddressRepository addressRepository;

    @Test
    public void findAllByContact_IdTest(){
        Iterable<Address> foundAdd=addressRepository.findAll();
        assertEquals(0, foundAdd.equals(0));


    }
}
