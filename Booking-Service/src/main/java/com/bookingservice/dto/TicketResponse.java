package com.bookingservice.dto;

import lombok.Data;

@Data
public class TicketResponse {

    private int ticketId;

    private String movieName;

    private String ticketStatus;

}
