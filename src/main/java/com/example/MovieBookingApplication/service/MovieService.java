package com.example.MovieBookingApplication.service;

import com.example.MovieBookingApplication.dto.MovieDto;
import com.example.MovieBookingApplication.entity.Movie;
import com.example.MovieBookingApplication.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(MovieDto movieDto) {

        Movie movie = Movie.builder()
                .title(movieDto.getTitle())
                .description(movieDto.getDescription())
                .duration(movieDto.getDuration())
                .genre(movieDto.getGenre())
                .releaseDate(movieDto.getReleaseDate())
                .language(movieDto.getLanguage())
                .build();

        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovie() {

        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(String genre) {

        Optional<List<Movie>> listOfMoviesBox = movieRepository.findByGenre(genre);

        if (listOfMoviesBox.isPresent()) {
            return listOfMoviesBox.get();
        } else throw new RuntimeException("No movie found for genre " + genre);
    }

    public List<Movie> getMoviesByLanguage(String language) {

        Optional<List<Movie>> moviesByLanguage = movieRepository.findMoviesByLanguage(language);

        if (moviesByLanguage.isPresent()) {
            return moviesByLanguage.get();
        } else throw new RuntimeException("No movie found of language " + language);
    }

    public List<Movie> getMovieByTitle(String title) {

        Optional<List<Movie>> moviesByTitle = movieRepository.findMoviesByTitle(title);

        if (moviesByTitle.isPresent()) {
            return moviesByTitle.get();
        } else throw new RuntimeException("No movie found of title " + title);
    }

    public Movie updateMovie(Long id, MovieDto movieDto) {

        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Movie found for the given Id " + id));

        Movie updatedMovie = Movie.builder()
                .id(existingMovie.getId())   // IMPORTANT: keep the same ID
                .title(movieDto.getTitle())
                .description(movieDto.getDescription())
                .duration(movieDto.getDuration())
                .genre(movieDto.getGenre())
                .releaseDate(movieDto.getReleaseDate())
                .language(movieDto.getLanguage())
                .build();

        return movieRepository.save(updatedMovie);
    }


    public void deleteMovie(Long id) {

        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Movie found for the given Id " + id));

        movieRepository.deleteById(id);
    }
}
