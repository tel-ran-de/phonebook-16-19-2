package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.ContactDto;
import com.telran.phonebookapi.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public ContactDto toDto(Contact contact) {
        return new ContactDto(contact.getId(), contact.getFirstName(), contact.getLastName(),
                contact.getAge(), contact.isFavorite(), contact.getGroup(), contact.getPhones(), contact.getAddresses(), contact.getEmails());
    }

    public Contact fromDto(ContactDto contactDto) {
        Contact contact = new Contact(contactDto.firstName, contactDto.lastName,
                contactDto.age, contactDto.isFavorite, contactDto.group);
        if (contactDto.phones.size() > 0)
            contactDto.phones.forEach(contact::addPhone);
        if (contactDto.addresses.size() > 0)
            contactDto.addresses.forEach(contact::addAddress);
        if (contactDto.emails.size() > 0)
            contactDto.emails.forEach(contact::addEmail);
        return contact;
    }
}
