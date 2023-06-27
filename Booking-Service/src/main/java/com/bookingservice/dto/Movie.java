package com.bookingservice.dto;

import com.bookingservice.enums.MovieStatus;
import lombok.Data;

@Data
public class Movie {

    private int movieId;

    private String movieName;

    private String genre;

    private String releaseDate;

    private String duration;

    private MovieStatus status;

    private int rating;
}
