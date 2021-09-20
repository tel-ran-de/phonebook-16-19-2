package com.telran.phonebookapi.dto;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Address;

public class AddressDto {
    public Long id;
    public String country;
    public String city;
    public String index;
    public String street;
    public String homeNr;
    public boolean isFavorite;
    public Long contactId;

    public AddressDto(Long id, String country, String city, String index, String street, String homeNr, boolean isFavorite, Long contactId) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.index = index;
        this.street = street;
        this.homeNr = homeNr;
        this.isFavorite = isFavorite;
        this.contactId = contactId;
    }

}
