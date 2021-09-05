package com.telran.phonebookapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String firsName;
    private String lastName;
    private int age;
    private boolean isFavorite;
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_group")
    private Group group;


    @OneToMany
    private List<Phone> phones;
    @OneToMany
    private List<Address> addresses;
    @OneToMany
    private List<Email> emails;

}

