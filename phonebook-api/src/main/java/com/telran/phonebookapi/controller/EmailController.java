package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.CreateEmailRequest;
import com.telran.phonebookapi.dto.CreateEmailResponse;
import com.telran.phonebookapi.dto.FullEmailResponse;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.mapper.EmailMapper;
import com.telran.phonebookapi.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService emailService;
    private final EmailMapper emailMapper;

    public EmailController(EmailService emailService, EmailMapper emailMapper) {
        this.emailService = emailService;
        this.emailMapper = emailMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEmailResponse addEmail(@Valid @RequestBody CreateEmailRequest createEmailRequest) {
        Email email = emailService.add(
                createEmailRequest.getEmail(),
                createEmailRequest.isFavorite(),
                createEmailRequest.getContactId()
        );
        return emailMapper.emailToRegisterEmailResponse(email);
    }

    @GetMapping("/{id}/all")
    public Iterable<FullEmailResponse> getAllEmails(@PathVariable Long id) {
        return emailService.getAll(id)
                .stream()
                .map(emailMapper::emailToFullEmailResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FullEmailResponse getEmailById(@PathVariable Long id) {
        Email email = emailService.get(id);
        return emailMapper.emailToFullEmailResponse(email);
    }

    @DeleteMapping("/{id}")
    public void deleteEmail(@PathVariable Long id) {
        emailService.remove(id);
    }

    @PutMapping("/{id}")
    public void updateEmail(@PathVariable Long id, @RequestBody CreateEmailRequest createEmailRequest) {
        emailService.edit(id, createEmailRequest.getEmail(), createEmailRequest.isFavorite());
    }
}
