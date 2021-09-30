package com.telran.phonebookapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreatePhoneResponse {
    private Long id;
    private String countryCode;
    private String telephoneNumber;
    private boolean favorite;

    public CreatePhoneResponse(Long id, @NotNull String countryCode, @NotNull String telephoneNumber, boolean favorite) {
        this.id = id;
        this.countryCode = countryCode;
        this.telephoneNumber = telephoneNumber;
        this.favorite = favorite;
    }
}
