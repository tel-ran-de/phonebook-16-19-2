package com.telran.phonebookapi.entity;

import lombok.*;

import javax.persistence.*;
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> 4beaaafc2bc5f3938282885b3409b6e90c46ed9b
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String firsName;
    @Setter
    private String lastName;
    @Setter
    private int age;
    @Setter
    private boolean isFavorite;
<<<<<<< HEAD
    // TODO: 06.09.2021
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_group")
    private Group group;

    @OneToMany(mappedBy = "contact")
    private List<Phone> phones;
    @OneToMany(mappedBy = "contact")
    private List<Address> addresses;
    @OneToMany(mappedBy = "contact")
=======
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_group")
    @Setter
    private Group group;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<Phone> phones;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<Address> addresses;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
>>>>>>> 4beaaafc2bc5f3938282885b3409b6e90c46ed9b
    private List<Email> emails;

    public Contact(@NonNull String firsName, @NonNull String lastName, int age, boolean isFavorite, @NonNull Group group) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.age = age;
        this.isFavorite = isFavorite;
        this.group = group;
<<<<<<< HEAD
=======
        phones = new ArrayList<>();
        addresses = new ArrayList<>();
        emails = new ArrayList<>();
>>>>>>> 4beaaafc2bc5f3938282885b3409b6e90c46ed9b
    }

    public void addAddress(@NonNull Address address) {
        this.addresses.add(address);
    }

    public void addEmail(@NonNull Email email) {
        this.emails.add(email);
    }

    public void addPhone(@NonNull Phone phone) {
        this.phones.add(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return age == contact.age && Objects.equals(id, contact.id) && Objects.equals(firsName, contact.firsName) && Objects.equals(lastName, contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firsName, lastName, age);
    }
}

