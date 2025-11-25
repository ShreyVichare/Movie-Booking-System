package com.example.MovieBookingApplication.dto;

import com.example.MovieBookingApplication.entity.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDto {

    private Integer numberOfSeats;

    private LocalDateTime time;

    private Double price;

    private BookingStatus bookingStatus;

    private List<String> seatNumber;

    private Long userId;

    private Long showId;
}
