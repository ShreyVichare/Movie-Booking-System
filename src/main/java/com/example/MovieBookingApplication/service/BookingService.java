package com.example.MovieBookingApplication.service;

import com.example.MovieBookingApplication.dto.BookingDto;
import com.example.MovieBookingApplication.entity.Booking;
import com.example.MovieBookingApplication.entity.BookingStatus;
import com.example.MovieBookingApplication.entity.Show;
import com.example.MovieBookingApplication.entity.User;
import com.example.MovieBookingApplication.repository.BookingRepository;
import com.example.MovieBookingApplication.repository.ShowRepository;
import com.example.MovieBookingApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking createBooking(BookingDto bookingDto) {

        User user = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found of ID " + bookingDto.getUserId()));
        Show show = showRepository.findById(bookingDto.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found of ID " + bookingDto.getShowId()));

        if (!isSeatAvailable(show, bookingDto.getNumberOfSeats())) {
            throw new RuntimeException("Not enough seat are available");
        }

        if (bookingDto.getSeatNumber().size() != bookingDto.getNumberOfSeats()) {
            throw new RuntimeException("seat number and number of seats must match");
        }

        validateDuplicateSeat(show, bookingDto.getSeatNumber());

        Booking booking = Booking.builder()
                .numberOfSeats(bookingDto.getNumberOfSeats())
                .time(LocalDateTime.now())
                .price(calculateTotalAmount(show.getPrice(), bookingDto.getNumberOfSeats()))
                .bookingStatus(BookingStatus.PENDING)
                .seatNumber(bookingDto.getSeatNumber())
                .show(show)
                .user(user)
                .build();

        return bookingRepository.save(booking);
    }

    private boolean isSeatAvailable(Show show, Integer numberOfSeats) {

        int bookedSeat = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();

        return (show.getTheater().getCapacity() - bookedSeat) >= numberOfSeats;
    }

    private void validateDuplicateSeat(Show show, List<String> seatNumbers) {

        Set<String> occupiedSeats = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .flatMap(booking -> booking.getSeatNumber().stream())
                .collect(Collectors.toSet());

        List<String> duplicateSeats = seatNumbers.stream()
                .filter(occupiedSeats::contains)
                .toList();

        if (!duplicateSeats.isEmpty()) {
            throw new RuntimeException("Seats are already booked");
        }
    }

    private Double calculateTotalAmount(Double price, Integer numberOfSeats) {

        return price * numberOfSeats;

    }


    public List<Booking> getUserBooking(Long userId) {

        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getShowBooking(Long showId) {

        return bookingRepository.findByShowId(showId);
    }

    public Booking confirmBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getBookingStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Booking is not in pending state");
        }

        //Payment Process
        booking.setBookingStatus(BookingStatus.CONFIRMED);

        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        validateCancellation(booking);

        booking.setBookingStatus(BookingStatus.CANCELLED);

        return bookingRepository.save(booking);
    }

    private void validateCancellation(Booking booking) {

        LocalDateTime showTime = booking.getShow().getTime();
        LocalDateTime deadlineTime = showTime.minusHours(2);

        if (LocalDateTime.now().isAfter(deadlineTime)) {
            throw new RuntimeException("Cannot cancel the booking");
        }

        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking Already been cancelled");
        }
    }


    public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {

        return bookingRepository.findByBookingStatus(bookingStatus);
    }


}
