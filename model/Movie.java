package com.csc3402.lab.ticketingsystem.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;


    private String movieName;
    private Integer duration;
    private String genre;


    @OneToMany(mappedBy = "movie")
    private List<Hall> halls;


    // Default constructor
    public Movie() {}


    // Parameterized constructor
    public Movie(String movieName, Integer duration, String genre) {
        this.movieName = movieName;
        this.duration = duration;
        this.genre = genre;
    }


    // Getters and setters
    public Long getMovieId() {
        return movieId;
    }


    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }


    public String getMovieName() {
        return movieName;
    }


    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }


    public Integer getDuration() {
        return duration;
    }


    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    public String getGenre() {
        return genre;
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Hall> getHalls() {
        return halls;
    }


    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                '}';
    }
}
