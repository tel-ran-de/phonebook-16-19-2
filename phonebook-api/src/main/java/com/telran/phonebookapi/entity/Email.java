package com.telran.phonebookapi.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Email {
    @Id
    @Column(name="email_id")
    private String emailId;

    private boolean isFavorite;

    @ManyToOne
    private Contact contact;

}
