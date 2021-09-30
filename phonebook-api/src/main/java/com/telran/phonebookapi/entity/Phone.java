package com.telran.phonebookapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String countryCode;
    @Setter
    private String telephoneNumber;
    @Setter
    private boolean favorite;

    @ManyToOne
    private Contact contact;

    public Phone(@NonNull String countryCode, @NonNull String telephoneNumber, boolean favorite, @NonNull Contact contact) {
        this.countryCode = countryCode;
        this.telephoneNumber = telephoneNumber;
        this.favorite = favorite;
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) && Objects.equals(countryCode, phone.countryCode) && Objects.equals(telephoneNumber, phone.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryCode, telephoneNumber);
    }
}
