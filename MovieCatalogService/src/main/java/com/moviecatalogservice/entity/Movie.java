package com.moviecatalogservice.entity;


import com.moviecatalogservice.enums.MovieStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieId;

    private String movieName;

    private String genre;

    private String releaseDate;

    private String duration;

    private MovieStatus status;

    private int rating;
}
