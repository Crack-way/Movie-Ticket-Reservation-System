package com.bookingservice.feignclient;

import com.bookingservice.dto.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "movie-service", path = "/movie-api")
public interface MovieClient {

    @GetMapping("getMovieById/{id}")
    ResponseEntity<Optional<Movie>> getMovieById(@PathVariable int id);


    @GetMapping("/getMovie/{movieName}")
    public ResponseEntity<Optional<Movie>> findMovieByName(@PathVariable String movieName);

    @GetMapping
    ResponseEntity<List<Movie>> getAllMovies();


    @GetMapping("/{movieName}")
    ResponseEntity<Void> removeMovieByName(@PathVariable String movieName);

    @PostMapping
    ResponseEntity<Movie> addMovie(@RequestBody Movie movie);
}
