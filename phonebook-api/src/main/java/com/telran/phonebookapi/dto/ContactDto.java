package com.telran.phonebookapi.dto;

import com.telran.phonebookapi.entity.Group;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ContactDto {
    @Positive
    public Long id;
    @NotBlank(message = "The field should not be blank")
    public String firstName;
    @NotBlank(message = "The field should not be blank")
    public String lastName;
    public int age;
    @Positive
    public boolean isFavorite;
    @Positive
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
