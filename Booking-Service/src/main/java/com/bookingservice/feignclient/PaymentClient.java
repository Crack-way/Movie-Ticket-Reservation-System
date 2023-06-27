package com.bookingservice.feignclient;


import com.bookingservice.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment-service", path = "/payment-api")
public interface PaymentClient {


    @GetMapping("/checkPayment/{paymentId}")
    ResponseEntity<PaymentResponse> checkPayment(@PathVariable int paymentId);
}
