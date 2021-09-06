package com.telran.phonebookapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String email;
    @Setter
    private boolean isFavorite;

    @ManyToOne
    private Contact contact;

    public Email(@NonNull String email, boolean isFavorite, @NonNull Contact contact) {
        this.email = email;
        this.isFavorite = isFavorite;
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email1 = (Email) o;
        return Objects.equals(id, email1.id) && Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
