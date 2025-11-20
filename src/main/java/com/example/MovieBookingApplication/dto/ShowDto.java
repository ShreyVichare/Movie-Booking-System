package com.example.MovieBookingApplication.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDto {

    private LocalDateTime time;

    private Double price;

    private Long movieId;

    private Long theaterId;
}
