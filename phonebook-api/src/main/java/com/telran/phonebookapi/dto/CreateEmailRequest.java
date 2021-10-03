package com.telran.phonebookapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmailRequest {
    @NotEmpty(message = "The field should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$", message = "Email is not valid. Please, try again")
    private String email;
    @JsonProperty("isFavorite")
    private boolean isFavorite;
    @Positive
    private long contactId;
}
