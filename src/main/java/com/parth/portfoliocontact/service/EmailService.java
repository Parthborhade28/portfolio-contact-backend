
package com.parth.portfoliocontact.service;

import com.parth.portfoliocontact.dto.ContactRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String recipientEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactEmail(ContactRequest request) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(recipientEmail);
        mail.setReplyTo(request.getEmail());
        mail.setSubject("Portfolio Contact: " + request.getSubject());

        mail.setText(
                "You received a new message through your portfolio.\n\n"
                + "Name: " + request.getName() + "\n"
                + "Email: " + request.getEmail() + "\n"
                + "Subject: " + request.getSubject() + "\n\n"
                + "Message:\n" + request.getMessage()
        );

        mailSender.send(mail);
    }
}
