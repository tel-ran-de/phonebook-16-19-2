package com.telran.phonebookapi.dto;

public class ErrorDto {
    public String errorMsg;
    public ErrorDto(String message) {
        this.errorMsg = message;
    }
}
