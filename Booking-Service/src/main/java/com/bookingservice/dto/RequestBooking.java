package com.bookingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBooking {

    private String username;

    private String movieName;

    private int ticketId;

    private int paymentId;

    private int movieId;


}
