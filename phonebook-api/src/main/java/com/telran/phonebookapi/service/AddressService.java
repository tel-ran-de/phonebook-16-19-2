package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Address;
import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.exception.AddressNotFoundException;
import com.telran.phonebookapi.exception.ContactNotFoundException;
import com.telran.phonebookapi.repository.AddressRepository;
import com.telran.phonebookapi.repository.ContactRepository;
import org.springframework.stereotype.Service;


@Service
public class AddressService {

    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;

    public AddressService(ContactRepository contactRepository, AddressRepository addressRepository) {
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
    }

    public void add(String country, String city, String index, String street, String homeNr, boolean isFavorite, long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("Contact with id " + contactId + " doesn't exist"));
        Address address = new Address(country, city, index, street, homeNr, isFavorite, contact);
        addressRepository.save(address);
    }

    public void edit(Long id, String country, String city, String index, String street, String homeNr, boolean isFavorite) {
        Address address = getById(id);
        address.setCountry(country);
        address.setCity(city);
        address.setIndex(index);
        address.setStreet(street);
        address.setHomeNr(homeNr);
        address.setFavorite(isFavorite);

        addressRepository.save(address);
    }

    public Address getById(long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address with id " + id + " not found"));
    }

    public void removeById(long id) {
        addressRepository.deleteById(id);
    }

    public Iterable<Address> getAll(long contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("Contact with id " + contactId + " not found")).getAddresses();


    }

}
