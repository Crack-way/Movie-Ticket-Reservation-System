package com.moviecatalogservice.service;

import com.moviecatalogservice.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

        List<Movie> getAllMovies();

        Movie addMovie(Movie movie);

        Optional<Movie> getMovieById(int id);

        void removeMovieByName(String movieName);

        Optional<Movie> findMovieByName(String name);
}
