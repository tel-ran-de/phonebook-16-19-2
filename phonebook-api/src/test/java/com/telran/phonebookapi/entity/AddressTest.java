package com.telran.phonebookapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddressTest {
    Address address;

    @Test
    public void addressCreatedTest() {
        address = new Address("De", "Be", "11111", "Str", "100D", false, new Contact());
        assertNotNull(address);
    }
}
