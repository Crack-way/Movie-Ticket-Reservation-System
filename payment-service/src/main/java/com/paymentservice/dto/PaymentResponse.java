package com.paymentservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PaymentResponse {


    private String paymentStatus;

    private double amount;

    private int userId;

    private int paymentId;

}
