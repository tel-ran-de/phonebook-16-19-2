package com.telran.phonebookapi.dto;


import lombok.Getter;

@Getter
public class FullEmailResponse {
    private long id;
    private String email;

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
