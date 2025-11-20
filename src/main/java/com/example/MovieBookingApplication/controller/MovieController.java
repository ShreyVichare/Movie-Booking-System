package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.MovieDto;
import com.example.MovieBookingApplication.entity.Movie;
import com.example.MovieBookingApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add-movie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDto movieDto) {

        return ResponseEntity.ok(movieService.addMovie(movieDto));
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> getAllMovie() {

        return ResponseEntity.ok(movieService.getAllMovie());
    }

    @GetMapping("/get-movies-by-genre")
    public ResponseEntity<List<Movie>> getMovieByGenre(@RequestParam String genre) {

        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
    }

    @GetMapping("/get-movies-by-language")
    public ResponseEntity<List<Movie>> getMovieByLanguage(@RequestParam String language) {

        return ResponseEntity.ok(movieService.getMoviesByLanguage(language));
    }

    @GetMapping("/get-movie-by-title")
    public ResponseEntity<List<Movie>> getMovieByTitle(@RequestParam String title) {

        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }

    @PutMapping("/update-movie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto) {

        return ResponseEntity.ok(movieService.updateMovie(id, movieDto));
    }

    @DeleteMapping("/delete-movie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {

        movieService.deleteMovie(id);

        return ResponseEntity.ok().build();
    }
}
