package com.telran.phonebookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhoneResponse {
    private long id;
    private String countryCode;
    private String telephoneNumber;
}
