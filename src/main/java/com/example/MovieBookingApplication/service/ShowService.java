package com.example.MovieBookingApplication.service;

import com.example.MovieBookingApplication.dto.ShowDto;
import com.example.MovieBookingApplication.entity.Movie;
import com.example.MovieBookingApplication.entity.Show;
import com.example.MovieBookingApplication.entity.Theater;
import com.example.MovieBookingApplication.repository.MovieRepository;
import com.example.MovieBookingApplication.repository.ShowRepository;
import com.example.MovieBookingApplication.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public Show createShow(ShowDto showDto) {

        Movie movie = movieRepository.findById(showDto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Not found movie of ID" + showDto.getMovieId()));

        Theater theater = theaterRepository.findById(showDto.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Not found Theater of ID " + showDto.getTheaterId()));
        Show show = Show.builder()
                .time(showDto.getTime())
                .price(showDto.getPrice())
                .movie(movie)
                .theater(theater)
                .build();

        return showRepository.save(show);
    }

    public List<Show> getAllShows() {

        return showRepository.findAll();
    }

    public List<Show> getShowsByMovie(Long movieId) {

        Optional<List<Show>> showsByMovie = showRepository.findByMovieId(movieId);

        if (showsByMovie.isPresent()) {
            return showsByMovie.get();
        } else throw new RuntimeException("Could not find Show for Movie ID " + movieId);
    }


    public List<Show> getShowsByTheater(Long theaterId) {
        Optional<List<Show>> showsByTheater = showRepository.findByTheaterId(theaterId);

        if (showsByTheater.isPresent()) {
            return showsByTheater.get();
        } else throw new RuntimeException("Could not find Show for Theater ID " + theaterId);
    }

    public Show updateShow(Long id, ShowDto showDto) {

        Show existingShow = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show does not exist for ID " + id));

        Movie movie = movieRepository.findById(showDto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found for ID " + showDto.getMovieId()));

        Theater theater = theaterRepository.findById(showDto.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found for ID " + showDto.getTheaterId()));

        
        existingShow.setTime(showDto.getTime());
        existingShow.setPrice(showDto.getPrice());
        existingShow.setMovie(movie);
        existingShow.setTheater(theater);

        return showRepository.save(existingShow);
    }


    public void deleteShow(Long id) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No show available for the id " + id));

        if (show.getBookings().isEmpty()) {
            showRepository.delete(show);
        } else {
            throw new RuntimeException("Can't delete show with existing bookings");
        }
    }

}
