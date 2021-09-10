package com.telran.phonebookapi.repository;

import com.telran.phonebookapi.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
