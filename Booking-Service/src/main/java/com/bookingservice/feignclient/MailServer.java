package com.bookingservice.feignclient;

import com.bookingservice.dto.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "mail-server", path = "/mail-api")
public interface MailServer {

    @PostMapping
    String sendEmail(Email email);
}
