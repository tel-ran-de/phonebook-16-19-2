package com.telran.phonebookapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class CreatePhoneRequest {
    @NotEmpty(message = "The field should not be empty")
    private String countryCode;
    @NotEmpty(message = "The field should not be empty")
    private String telephoneNumber;
    @JsonProperty("favorite")
    private boolean favorite;
    @Positive
    private Long contactId;

    public CreatePhoneRequest(@NotNull String countryCode, @NotNull String telephoneNumber, boolean favorite, @NotNull Long contactId) {
        this.countryCode = countryCode;
        this.telephoneNumber = telephoneNumber;
        this.favorite = favorite;
        this.contactId = contactId;
    }
}
