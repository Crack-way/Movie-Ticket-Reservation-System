package com.emailservice.service;

import com.emailservice.entity.Email;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    String sendEmail(Email email);


}
