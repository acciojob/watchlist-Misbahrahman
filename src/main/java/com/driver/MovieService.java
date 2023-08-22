package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public ResponseEntity addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }

    public ResponseEntity addDirector(Director director) {
        return movieRepository.addDirector(director);
    }

    public ResponseEntity addMovieDirectorPair(String movieName, String directorName) {
        return movieRepository.addMovieDirectorPair(directorName , movieName);
    }

    public ResponseEntity getMovieByName(String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

    public ResponseEntity getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }

    public ResponseEntity getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public ResponseEntity findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public ResponseEntity deleteDitrectorByName(String directorName) {
        return movieRepository.deleteDitrectorByName(directorName);
    }

    public ResponseEntity deleteAllDirectors() {
        return movieRepository.deleteAllDirectors();
    }
}
