package com.telran.phonebookapi.mapper;

import com.telran.phonebookapi.dto.CreateEmailResponse;
import com.telran.phonebookapi.dto.FullEmailResponse;
import com.telran.phonebookapi.entity.Email;

public class EmailMapper {
     public static CreateEmailResponse emailToRegisterEmailResponse(Email email) {
        CreateEmailResponse createEmailResponse = new CreateEmailResponse();
        createEmailResponse.setId(email.getId());
        return createEmailResponse;
     }

    public static FullEmailResponse emailToFullEmailResponse (Email email) {
        FullEmailResponse fullEmailResponse = new FullEmailResponse();
        fullEmailResponse.setEmail(email.getEmail());
        fullEmailResponse.setId(email.getId());
        return fullEmailResponse;
    }
}
