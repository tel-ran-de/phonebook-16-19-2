package com.telran.phonebookapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class CreatePhoneResponse {
    @Positive
    private Long id;
    @NotEmpty(message = "The field should not be empty")
    private String countryCode;
    @NotEmpty(message = "The field should not be empty")
    private String telephoneNumber;
    @Positive
    private boolean favorite;

    public CreatePhoneResponse(Long id, @NotNull String countryCode, @NotNull String telephoneNumber, boolean favorite) {
        this.id = id;
        this.countryCode = countryCode;
        this.telephoneNumber = telephoneNumber;
        this.favorite = favorite;
    }
}
