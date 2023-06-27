package com.bookingservice.controller;


import com.bookingservice.dto.TicketRequest;
import com.bookingservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket-api")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Boolean> addTicket(@RequestBody TicketRequest ticketRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.addTicket(ticketRequest));
    }




}
