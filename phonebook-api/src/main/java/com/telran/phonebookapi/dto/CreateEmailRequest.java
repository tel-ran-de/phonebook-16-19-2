package com.telran.phonebookapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class CreateEmailRequest {
    @NotEmpty @Email
    private String email;
    @JsonProperty("isFavorite")
    private boolean isFavorite;
    private long contactId;
}
