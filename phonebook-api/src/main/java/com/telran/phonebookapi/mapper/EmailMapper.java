package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.CreateEmailResponse;
import com.telran.phonebookapi.dto.FullEmailResponse;
import com.telran.phonebookapi.entity.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {
     public CreateEmailResponse emailToRegisterEmailResponse(Email email) {
        return new CreateEmailResponse(email.getId());
     }

    public FullEmailResponse emailToFullEmailResponse (Email email) {
        return new FullEmailResponse(email.getId(), email.getEmail());
    }
}
