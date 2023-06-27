package com.bookingservice.dto;


import lombok.Data;

@Data
public class PaymentResponse {


    private String paymentStatus;

    private double amount;

    private int userId;
}
