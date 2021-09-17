package com.telran.phonebookapi.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
public class CreateEmailRequest {
    @NotEmpty @Email
    private String email;
    private boolean favorite;
    private long contactId;

    public CreateEmailRequest(String email, boolean favorite, long contactId) {
        this.email = email;
        this.favorite = favorite;
        this.contactId = contactId;
    }
}
