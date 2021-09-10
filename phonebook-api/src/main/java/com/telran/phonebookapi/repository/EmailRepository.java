package com.telran.phonebookapi.repository;

import com.telran.phonebookapi.entity.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long> {
}
