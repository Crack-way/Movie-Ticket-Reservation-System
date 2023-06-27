package com.bookingservice.controller;


import com.bookingservice.exception.MovieDoesNotExistException;
import com.bookingservice.exception.TicketAlreadyBookedWithThisPaymentException;
import com.bookingservice.exception.TicketNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MovieDoesNotExistException .class)
    public ResponseEntity<String> handleMovieDoesNoteExistException(MovieDoesNotExistException ex) {
        // Custom error handling logic
        // Return a response entity with an appropriate error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie Does Not Exist");
    }

    @ExceptionHandler(TicketAlreadyBookedWithThisPaymentException.class)
    public ResponseEntity<String> handleTicketAlreadyBookedWithThisPaymentException(TicketAlreadyBookedWithThisPaymentException ex) {
        // Custom error handling logic
        // Return a response entity with an appropriate error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Already Booked with this payment");
    }
    @ExceptionHandler(TicketNotAvailableException.class)
    public ResponseEntity<String> handleTicketNotAvailableException(TicketNotAvailableException ex) {
        // Custom error handling logic
        // Return a response entity with an appropriate error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Not Available");
    }



}
