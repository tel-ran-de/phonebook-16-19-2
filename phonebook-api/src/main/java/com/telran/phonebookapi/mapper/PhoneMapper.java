package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.CreatePhoneResponse;
import com.telran.phonebookapi.entity.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {
    public CreatePhoneResponse phoneToRegisterPhoneResponse(Phone phone) {
        return new CreatePhoneResponse(phone.getId(), phone.getCountryCode(), phone.getTelephoneNumber());
    }
}
