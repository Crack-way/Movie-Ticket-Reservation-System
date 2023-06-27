package com.bookingservice.controller;

import com.bookingservice.dto.BookingResponse;
import com.bookingservice.dto.RequestBooking;
import com.bookingservice.dto.TicketResponse;
import com.bookingservice.entity.Ticket;
import com.bookingservice.service.BookingService;
import com.bookingservice.service.TicketService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-api")
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    private final BookingService bookingService;
    private final TicketService ticketService;

    @PostMapping
    @CircuitBreaker(name="bookingBreaker",fallbackMethod = "bookingBreakerFallback")
    public ResponseEntity<BookingResponse> requestBooking(@RequestBody RequestBooking requestBooking){


        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.requestBooking(requestBooking));
    }

    public ResponseEntity<BookingResponse> bookingBreakerFallback(RequestBooking requestBooking,Exception ex){

         log.info("Fallback is executed because service is down:",ex.getMessage());
         BookingResponse bookingResponse=new BookingResponse();
         return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(bookingResponse);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> showAllAvailableTicket(){

        return ResponseEntity.ok(ticketService.showAvailableTicket());
    }


}
