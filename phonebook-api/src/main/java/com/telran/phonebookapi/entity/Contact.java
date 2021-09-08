package com.telran.phonebookapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private int age;
    @Setter
    private boolean isFavorite;
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_group")
    @Setter
    private Group group;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<Phone> phones = new ArrayList<>(); // to avoid null pointer exception when creating entity via default constructor and adding new phone
    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<Address> addresses = new ArrayList<>();
    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE)
    private List<Email> emails = new ArrayList<>();

    public Contact(@NonNull String firstName, @NonNull String lastName, int age, boolean isFavorite, @NonNull Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isFavorite = isFavorite;
        this.group = group;
//        phones = new ArrayList<>();
//        addresses = new ArrayList<>();
//        emails = new ArrayList<>();
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
        return age == contact.age && Objects.equals(id, contact.id) && Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age);
    }
}

