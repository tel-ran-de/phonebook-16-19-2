package com.telran.phonebookapi.dto;

import com.telran.phonebookapi.entity.Group;

public class ContactDto {
    public Long id;
    public String firstName;
    public String lastName;
    public int age;
    public boolean isFavorite;
    public String group;

    public ContactDto(Long id, String firstName, String lastName, int age, boolean isFavorite, Group group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isFavorite = isFavorite;
        this.group = group.toString();
    }
}
