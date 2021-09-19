package com.telran.phonebookapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhoneRequest {
    @NotEmpty(message = "The field should not be empty")
    private String countryCode;
    @NotEmpty(message = "The field should not be empty")
    private String telephoneNumber;
    @JsonProperty("isFavorite")
    private boolean isFavorite;
    @Positive
    private long contactId;

}
