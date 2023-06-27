package com.moviecatalogservice.service;


import com.moviecatalogservice.entity.Movie;
import com.moviecatalogservice.enums.MovieStatus;
import com.moviecatalogservice.exception.MovieDoesNotExistException;
import com.moviecatalogservice.repo.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepo movieRepo;
    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public Movie addMovie(Movie movie) {
        movie.setStatus(MovieStatus.Showing);
        return movieRepo.save(movie);
    }

    @Override
    public Optional<Movie> getMovieById(int id) {
        return movieRepo.findByMovieId(id);

    }

    @Override
    public void removeMovieByName(String movieName) {

        movieRepo.removeByMovieName(movieName);

    }

    @Override
    public Optional<Movie> findMovieByName(String name) {
        return movieRepo.findByMovieName(name);
    }
}
