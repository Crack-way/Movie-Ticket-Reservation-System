package com.bookingservice.service;

import com.bookingservice.dto.TicketRequest;
import com.bookingservice.dto.TicketResponse;
import com.bookingservice.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Optional<Ticket> bookTicket(Ticket ticket);

    List<TicketResponse> showAvailableTicket();

    boolean addTicket(TicketRequest ticketRequest);

    TicketResponse findTicketById(int ticketId);
}
