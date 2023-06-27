package com.bookingservice.entity;

import com.bookingservice.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    private String userName;

    private int ticketId;

    private int paymentId;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;


}
