package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.ContactDto;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.mapper.ContactMapper;
import com.telran.phonebookapi.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService contactService;
    private final ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }


    @PostMapping("")
    public ContactDto add(@RequestBody ContactDto contactDto) {
        if (contactDto.firstName != null && contactDto.lastName != null && contactDto.group != null) {
            Contact contact = contactService.add(contactDto.firstName, contactDto.lastName, contactDto.age, contactDto.isFavorite, contactDto.group);
            return contactMapper.toDto(contact);
        }
        return null;
    }

    @PutMapping("/{id}")
    public void edit(@RequestBody ContactDto contactDto, @PathVariable Long id) {
        contactService.editContact(id, contactDto.firstName, contactDto.lastName, contactDto.age, contactDto.isFavorite, contactDto.group);
    }

    @GetMapping("")
    public List<ContactDto> getAll() {
        List<Contact> contacts = (List<Contact>) contactService.getAll();
        return contacts.stream().map(contactMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ContactDto getById(@PathVariable Long id) {
        return contactMapper.toDto(contactService.get(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        contactService.remove(id);
    }
}
