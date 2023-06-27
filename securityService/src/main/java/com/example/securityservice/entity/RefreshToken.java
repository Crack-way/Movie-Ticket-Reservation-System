package com.example.securityservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String token;

    private Instant expiryDate;


    @OneToOne
    @JoinColumn(name="user_Id",referencedColumnName = "userId")
    private UserCredentials user;


}
