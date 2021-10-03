package com.telran.phonebookapi.dto;


import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class AddressDto {
    @Positive
    public Long id;
    @ApiModelProperty(example = "Germany")
    @NotEmpty(message = "The field should not be blank")
    public String country;
    @NotBlank(message = "The field should not be blank")
    @ApiModelProperty(example = "Berlin")
    public String city;
    @NotBlank(message = "The field should not be blank")
    @ApiModelProperty(example = "76137")
    public String index;
    @NotBlank(message = "The field should not be blank")
    @ApiModelProperty(example = "Marienplatz")
    public String street;
    @NotBlank(message = "The field should not be blank")
    @ApiModelProperty(example = "22a")
    public String homeNr;
    public boolean isFavorite;
    @Positive
    @ApiModelProperty(example = "123")
    public Long contactId;

    public AddressDto(Long id, String country, String city, String index, String street, String homeNr, boolean isFavorite, Long contactId) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.index = index;
        this.street = street;
        this.homeNr = homeNr;
        this.isFavorite = isFavorite;
        this.contactId = contactId;
    }

}
