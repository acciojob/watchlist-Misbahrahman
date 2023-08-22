package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class MovieRepository {

    //name -> movie
    HashMap<String , Movie> movies = new HashMap<>();

    //name->director
    HashMap<String , Director> directors = new HashMap<>();

    //moviesByDirector.get(directors.get(name))
    //nameOfDirector Mapped to MovieObject
    HashMap<String , List<String>> moviesByDirectors = new HashMap<>();


    public ResponseEntity addMovie(Movie movie) {
        movies.put(movie.getName() , movie);
        return new ResponseEntity("Successfully Added " + movie.getName() , HttpStatus.OK);
    }

    public ResponseEntity addDirector(Director director) {
        directors.put(director.getName() , director);
        return new ResponseEntity("Successfully Added " + director.getName() , HttpStatus.OK);
    }

    public ResponseEntity addMovieDirectorPair(String directorName, String movieName) {
        if(!movies.containsKey(movieName)){
            return new ResponseEntity("Movie Not Found" , HttpStatus.NOT_FOUND);
        }

        if(!directors.containsKey(directorName)){
            return new ResponseEntity("Director Not Found" , HttpStatus.NOT_FOUND);
        }

        //if Both Movie and director in database
        if(moviesByDirectors.containsKey(directorName)){
            moviesByDirectors.get(directorName).add(movieName);
        }else{
            List<String> list = new ArrayList<>();
            list.add(movieName);
            moviesByDirectors.put(directorName , list);
        }
        return new ResponseEntity("Succesfully Added " + movieName + " to " + directorName , HttpStatus.OK);
    }


    public ResponseEntity getMovieByName(String movieName) {
        if(movies.containsKey(movieName))return new ResponseEntity(movies.get(movieName) , HttpStatus.OK);
        return new ResponseEntity("Movie Not Found" , HttpStatus.NOT_FOUND);
    }


    public ResponseEntity getDirectorByName(String directorName) {
        if(directors.containsKey(directorName))return new ResponseEntity(directors.get(directorName) , HttpStatus.OK);
        else return new ResponseEntity(null , HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity getMoviesByDirectorName(String directorName) {
        if(moviesByDirectors.containsKey(directorName)){
            return new ResponseEntity(moviesByDirectors.get(directorName) , HttpStatus.OK);
        }else{
            String errorMessage = "Director not found for name: " + directorName;
            return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity findAllMovies() {
        if(movies.size() > 0)return new ResponseEntity(getListOfAllMovies() , HttpStatus.OK);
        return new ResponseEntity("No movies Added yet" , HttpStatus.NOT_FOUND);
    }


    List<String> getListOfAllMovies(){
        List<String> list = new ArrayList<>();
        for(String x : movies.keySet()){
            list.add(x);
        }
        return list;
    }


    public ResponseEntity deleteDitrectorByName(String directorName) {
        if(directors.containsKey(directorName)){
            //del director from db
            directors.remove(directorName);
            //del all his movies
            for(String movie : moviesByDirectors.get(directorName)){
                movies.remove(movie);
            }
            //del from director pairDb
            moviesByDirectors.remove(directorName);
            return new ResponseEntity("Successfully Deleted " + directorName + " from database" , HttpStatus.OK);
        }
        return new ResponseEntity("Director Not Found" , HttpStatus.NOT_FOUND);
    }


    public ResponseEntity deleteAllDirectors() {

        for(String director : directors.keySet()){
            //calling earlier made func
            if(moviesByDirectors.containsKey(director))deleteDitrectorByName(director);
            else directors.remove(director);
        }
        return new ResponseEntity("Success", HttpStatus.OK);
    }
}
