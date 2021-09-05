package com.telran.phonebookapi.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String country;
    private String city;
    private String index;
    private String address;
    private String homeNr;
    private boolean isFavorite;

    @ManyToOne
    private Contact contact;
}
