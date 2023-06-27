package com.paymentservice.controller;


import com.paymentservice.dto.PaymentRequest;
import com.paymentservice.dto.PaymentResponse;
import com.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-api")
public class PaymentController {


    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.makePayment(paymentRequest));

    }

    @PostMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> updatePayment(@RequestBody PaymentRequest paymentRequest, @PathVariable int paymentId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.updatePayment(paymentRequest, paymentId));
    }


    @GetMapping("/checkPayment/{paymentId}")
    public ResponseEntity<PaymentResponse> checkPayment(@PathVariable int paymentId){

        return ResponseEntity.ok().body(paymentService.checkPayment(paymentId));
    }

}
