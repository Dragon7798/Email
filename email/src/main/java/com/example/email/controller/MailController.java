package com.example.email.controller;


import com.example.email.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailController {


    @Autowired
    MailService mailService;

    @GetMapping("/get")
    boolean sendMail() {
        mailService.send();
        return true;
    }

    @GetMapping("/html")
    boolean sendHtmlEmail() throws MessagingException {
        mailService.htmlEmail();
        return true;
    }


    @GetMapping("/attachment")
    boolean sendMailWithAttachment() throws MessagingException {
        mailService.mailWithAttachment();
        return true;
    }

    @GetMapping("/image")
    boolean sendmailWithImage() throws MessagingException {
        mailService.mailWithImage();
        return true;
    }

}
