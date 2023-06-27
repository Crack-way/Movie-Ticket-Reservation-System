package com.bookingservice.dto;


import lombok.Data;

import java.util.Date;

@Data
public class BookingResponse {

    private int ticketId;

    private String bookingStatus;

    private Date bookingDate;

}
