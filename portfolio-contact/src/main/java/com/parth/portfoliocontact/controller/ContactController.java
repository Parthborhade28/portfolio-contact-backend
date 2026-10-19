
package com.parth.portfoliocontact.controller;

import com.parth.portfoliocontact.dto.ContactRequest;
import com.parth.portfoliocontact.service.EmailService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> submitContact(
            @Valid @RequestBody ContactRequest request) {

        emailService.sendContactEmail(request);

        return ResponseEntity.ok(
                Map.of("message",
                        "Your message has been sent successfully!")
        );
    }
}
