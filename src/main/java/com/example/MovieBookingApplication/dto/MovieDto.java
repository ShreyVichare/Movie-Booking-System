package com.example.MovieBookingApplication.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDto {

    private String title;

    private String description;

    private String genre;

    private Integer duration;

    private LocalDate releaseDate;

    private String language;
}
