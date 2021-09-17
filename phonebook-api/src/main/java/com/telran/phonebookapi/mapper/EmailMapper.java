package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.CreateEmailResponse;
import com.telran.phonebookapi.dto.FullEmailResponse;
import com.telran.phonebookapi.entity.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {
     public CreateEmailResponse emailToRegisterEmailResponse(Email email) {
        CreateEmailResponse createEmailResponse = new CreateEmailResponse();
        createEmailResponse.setId(email.getId());
        return createEmailResponse;
     }

    public FullEmailResponse emailToFullEmailResponse (Email email) {
        FullEmailResponse fullEmailResponse = new FullEmailResponse();
        fullEmailResponse.setEmail(email.getEmail());
        fullEmailResponse.setId(email.getId());
        return fullEmailResponse;
    }
}
