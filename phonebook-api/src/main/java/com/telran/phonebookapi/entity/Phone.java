package com.telran.phonebookapi.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String countryCode;
    private String telephoneNumber;
    private boolean isFavorite;

    @ManyToOne
    private Contact contact;

}
