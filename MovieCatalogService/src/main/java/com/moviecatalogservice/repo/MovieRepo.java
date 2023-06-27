package com.moviecatalogservice.repo;

import com.moviecatalogservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {

    void removeByMovieName(String movieName);


    Optional<Movie> findByMovieName(String movieName);


    Optional<Movie> findByMovieId(int id);
}
