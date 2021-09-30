package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.ErrorDto;
import com.telran.phonebookapi.exception.AddressNotFoundException;
import com.telran.phonebookapi.exception.ContactNotFoundException;
import com.telran.phonebookapi.exception.EmailNotFoundException;
import com.telran.phonebookapi.exception.PhoneNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {

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

    @ExceptionHandler(PhoneNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handlePhoneNotFoundException(PhoneNotFoundException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handlerAddressNotFoundException(AddressNotFoundException e) {
        return new ErrorDto(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

