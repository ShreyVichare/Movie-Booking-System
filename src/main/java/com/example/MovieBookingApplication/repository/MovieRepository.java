package com.example.MovieBookingApplication.repository;

import com.example.MovieBookingApplication.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

//    public Optional<Movie> findById(Long Id);

    public Optional<List<Movie>> findByGenre(String genre);

    public Optional<List<Movie>> findMoviesByLanguage(String language);

    public Optional<List<Movie>> findMoviesByTitle(String title);
}
