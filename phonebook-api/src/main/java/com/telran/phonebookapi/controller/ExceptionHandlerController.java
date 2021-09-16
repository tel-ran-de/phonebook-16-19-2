package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.ErrorDto;
import com.telran.phonebookapi.exception.ContactNotFoundException;
import com.telran.phonebookapi.exception.EmailExistsException;
import com.telran.phonebookapi.exception.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(EmailExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handleEmailExistsException(EmailExistsException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(ContactNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleContactNotFoundException(ContactNotFoundException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleEmailNotFoundException(EmailNotFoundException e) {
        return new ErrorDto(e.getMessage());
    }
}
