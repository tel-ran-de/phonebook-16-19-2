package com.telran.phonebookapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmailRequest {
    @NotEmpty(message = "The field should not be empty")
    @Pattern(regexp = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+", message = "Email is not valid. Please, try again")
    private String email;
    @JsonProperty("isFavorite")
    private boolean isFavorite;
    @Positive
    private long contactId;
}
