package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.ContactDto;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public ContactDto toDto(Contact contact) {
        return new ContactDto(contact.getId(), contact.getFirstName(), contact.getLastName(),
                contact.getAge(), contact.isFavorite(), contact.getGroup());
    }

    public Contact fromDto(ContactDto contactDto) {
        Group group = Group.valueOf(contactDto.group);
        return new Contact(contactDto.firstName, contactDto.lastName,
                contactDto.age, contactDto.isFavorite, group);
    }
}
