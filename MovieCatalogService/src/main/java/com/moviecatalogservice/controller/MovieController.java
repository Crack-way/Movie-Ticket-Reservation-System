package com.moviecatalogservice.controller;


import com.moviecatalogservice.entity.Movie;
import com.moviecatalogservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie-api")
public class MovieController {


    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {

        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("getMovieById/{id}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable int id) {

        return ResponseEntity.ok(movieService.getMovieById(id));

    }

    @GetMapping("/{movieName}")
    public ResponseEntity<Void> removeMovieByName(@PathVariable String movieName) {

        movieService.removeMovieByName(movieName);

        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {

        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.addMovie(movie));
    }

    @GetMapping("/getMovie/{movieName}")
    public ResponseEntity<Optional<Movie>> findMovieByName(@PathVariable String movieName){

        return ResponseEntity.ok().body(movieService.findMovieByName(movieName));
    }


}
