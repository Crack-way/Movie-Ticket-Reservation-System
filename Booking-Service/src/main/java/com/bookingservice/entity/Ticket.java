package com.bookingservice.entity;

import com.bookingservice.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;

    private String movieName;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    private int bookingId;

    private double ticketPrice;


}
