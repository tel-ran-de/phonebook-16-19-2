package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.AddressDto;
import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.mapper.AddressMapper;
import com.telran.phonebookapi.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;


    public AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto addAddress(@Valid @RequestBody AddressDto addressDto) {
        Address address = addressService.add(addressDto.country, addressDto.city, addressDto.index,
                addressDto.street, addressDto.homeNr, addressDto.isFavorite, addressDto.contactId);
        return addressMapper.toDto(address);
    }

    @PutMapping("/{id}")
    public void editAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Long id) {
        addressService.edit(id, addressDto.country, addressDto.city, addressDto.index,
                addressDto.street, addressDto.homeNr, addressDto.isFavorite);
    }

    @GetMapping("/{id}")
    public AddressDto getById(@Valid @Positive @PathVariable Long id) {
        return addressMapper.toDto(addressService.getById(id));
    }

    @GetMapping("/{id}/all")
    public Iterable<AddressDto> getAllAddress(@Valid @PathVariable Long id) {
        List<Address> addresses = (List<Address>) (addressService.getAll(id));
        return addresses.stream().map(addressMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteAddressById(@Valid @PathVariable Long id) {
        addressService.removeById(id);
    }

}
