package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public ResponseEntity<String> addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }

    public ResponseEntity<String> addDirector(Director director) {
        return movieRepository.addDirector(director);
    }

    public ResponseEntity<String> addMovieDirectorPair(String movieName, String directorName) {
        return movieRepository.addMovieDirectorPair(directorName , movieName);
    }

    public ResponseEntity<Movie> getMovieByName(String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

    public ResponseEntity<Director> getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }

    public ResponseEntity<List<String>> getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public ResponseEntity<List<String>> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public ResponseEntity<String> deleteDitrectorByName(String directorName) {
        return movieRepository.deleteDitrectorByName(directorName);
    }

    public ResponseEntity<String> deleteAllDirectors() {
        return movieRepository.deleteAllDirectors();
    }
}
