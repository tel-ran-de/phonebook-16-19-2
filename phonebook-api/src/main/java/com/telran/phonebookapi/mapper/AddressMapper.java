package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.AddressDto;
import com.telran.phonebookapi.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressDto toDto(Address address) {
        return new AddressDto(address.getId(), address.getCountry(), address.getCity(), address.getIndex(),
                address.getStreet(), address.getHomeNr(), address.isFavorite(), address.getContact().getId());
    }
}
