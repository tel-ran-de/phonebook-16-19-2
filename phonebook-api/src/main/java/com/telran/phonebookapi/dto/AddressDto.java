package com.telran.phonebookapi.dto;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Address;

public class AddressDto {
    public long id;
    public String country;
    public String city;
    public String index;
    public String street;
    public String homeNr;
    public boolean isFavorite;
    public long contactId;

    public AddressDto(long id, String country, String city, String index, String street, String homeNr, boolean isFavorite, long contactId) {
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
