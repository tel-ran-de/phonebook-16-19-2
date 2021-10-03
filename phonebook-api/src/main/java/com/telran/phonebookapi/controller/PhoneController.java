package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.CreatePhoneRequest;
import com.telran.phonebookapi.dto.CreatePhoneResponse;
import com.telran.phonebookapi.entity.Phone;
import com.telran.phonebookapi.mapper.PhoneMapper;
import com.telran.phonebookapi.service.PhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/phone")
public class PhoneController {
    private final PhoneService phoneService;
    private final PhoneMapper phoneMapper;

    public PhoneController(PhoneService phoneService, PhoneMapper phoneMapper) {
        this.phoneService = phoneService;
        this.phoneMapper = phoneMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePhoneResponse addPhone(@Valid @RequestBody CreatePhoneRequest createPhoneRequest) {
        Phone phone = phoneService.add(
                createPhoneRequest.getCountryCode(),
                createPhoneRequest.getTelephoneNumber(),
                createPhoneRequest.isFavorite(),
                createPhoneRequest.getContactId()
        );
        return phoneMapper.phoneToRegisterPhoneResponse(phone);
    }

    @GetMapping("/{id}/all")
    public List<CreatePhoneResponse> getAllPhones(@PathVariable Long id) {
        return phoneService.getAll(id)
                .stream()
                .map(phoneMapper::phoneToRegisterPhoneResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CreatePhoneResponse getPhoneById(@Valid @PathVariable Long id) {
        Phone phone = phoneService.get(id);
        return phoneMapper.phoneToRegisterPhoneResponse(phone);
    }

    @DeleteMapping("/{id}")
    public void deletePhone(@Valid @PathVariable Long id) {
        phoneService.remove(id);
    }

    @PutMapping("/{id}")
    public void updatePhone(@Valid @PathVariable Long id, @Valid @RequestBody CreatePhoneRequest createPhoneRequest) {
        phoneService.edit(
                id,
                createPhoneRequest.getCountryCode(),
                createPhoneRequest.getTelephoneNumber(),
                createPhoneRequest.isFavorite()
        );
    }
}
