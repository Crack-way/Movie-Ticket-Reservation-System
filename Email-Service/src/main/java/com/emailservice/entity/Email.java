package com.emailservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


@Entity
@Data
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Email
    private String to;

    private String cc;
    private String subject;
    private String body;
}
