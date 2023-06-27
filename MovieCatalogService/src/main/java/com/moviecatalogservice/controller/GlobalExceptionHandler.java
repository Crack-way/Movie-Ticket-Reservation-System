package com.moviecatalogservice.controller;


import com.moviecatalogservice.exception.MovieDoesNotExistException;
import com.moviecatalogservice.exception.TicketDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(MovieDoesNotExistException.class)
    public ResponseEntity<String> handleMovieDoesNoteExistException(MovieDoesNotExistException ex) {
        // Custom error handling logic
        // Return a response entity with an appropriate error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie Does Not Exist");
    }

    @ExceptionHandler(TicketDoesntExistException.class)
    public ResponseEntity<String> handleTicketNotAvailableException(TicketDoesntExistException ex) {
        // Custom error handling logic
        // Return a response entity with an appropriate error message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket Not Available");
    }

}
