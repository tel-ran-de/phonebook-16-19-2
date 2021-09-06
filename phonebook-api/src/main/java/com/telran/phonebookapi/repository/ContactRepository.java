package com.telran.phonebookapi.repository;

import com.telran.phonebookapi.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
