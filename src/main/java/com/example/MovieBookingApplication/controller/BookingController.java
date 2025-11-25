package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.BookingDto;
import com.example.MovieBookingApplication.entity.Booking;
import com.example.MovieBookingApplication.entity.BookingStatus;
import com.example.MovieBookingApplication.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create-booking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto bookingDto) {

        return ResponseEntity.ok(bookingService.createBooking(bookingDto));
    }

    @GetMapping("/get-user-booking/{id}")
    public ResponseEntity<List<Booking>> getUserBooking(@PathVariable Long id) {

        return ResponseEntity.ok(bookingService.getUserBooking(id));
    }

    @GetMapping("/get-show-booking/{id}")
    public ResponseEntity<List<Booking>> getShowBooking(@PathVariable Long id) {

        return ResponseEntity.ok(bookingService.getShowBooking(id));
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

    @GetMapping("/get-booking-by-status/{bookingStatus}")
    public ResponseEntity<List<Booking>> getBookingByStatus(@PathVariable BookingStatus bookingStatus) {
        return ResponseEntity.ok(bookingService.getBookingByStatus(bookingStatus));
    }
}
