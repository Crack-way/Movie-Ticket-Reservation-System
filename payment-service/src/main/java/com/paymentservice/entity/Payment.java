package com.paymentservice.entity;

import com.paymentservice.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int payment_id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private double amount;

    private int user_id;
}
