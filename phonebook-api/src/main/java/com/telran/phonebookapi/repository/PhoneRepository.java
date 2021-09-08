package com.telran.phonebookapi.repository;

import com.telran.phonebookapi.entity.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
