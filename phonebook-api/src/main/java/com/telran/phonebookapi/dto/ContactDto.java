package com.telran.phonebookapi.dto;

import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.entity.Group;
import com.telran.phonebookapi.entity.Phone;

import java.util.List;

public class ContactDto {
    public Long id;
    public String firstName;
    public String lastName;
    public int age;
    public boolean isFavorite;
    public Group group;
    public List<Phone> phones;
    public List<Address> addresses;
    public List<Email> emails;

    public ContactDto(Long id, String firstName, String lastName, int age, boolean isFavorite, Group group,
                      List<Phone> phones, List<Address> addresses, List<Email> emails) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isFavorite = isFavorite;
        this.group = group;
        this.phones = phones;
        this.addresses = addresses;
        this.emails = emails;
    }
}
