package com.telran.phonebookapi.repository;

import com.telran.phonebookapi.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
