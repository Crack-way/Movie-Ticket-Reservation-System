package com.emailservice.controller;


import com.emailservice.entity.Email;
import com.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail-api")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;


    @PostMapping
    public String sendEmail(@RequestBody Email email) {

        return emailService.sendEmail(email);

    }


}
