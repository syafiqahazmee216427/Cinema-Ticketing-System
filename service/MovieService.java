package com.csc3402.lab.ticketingsystem.service;

import com.csc3402.lab.ticketingsystem.model.Movie;
import com.csc3402.lab.ticketingsystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeatingService seatingService;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Method to retrieve all movies from the database
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Method to retrieve a movie by its ID
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    // Method to retrieve available dates for booking a movie
    public List<String> getAvailableDates(Long movieId) {
        LocalDate today = LocalDate.now();


        return today.datesUntil(today.plusDays(4))
                .map(date -> date.format(dateFormatter))  // Format the date to "dd-MM-yyyy"
                .collect(Collectors.toList());
    }

    // Method to retrieve available showtimes for a movie
    public List<String> getAvailableShowtimes(Long movieId) {
        // Static list of showtimes. You can modify this to be dynamic based on the movie or cinema.
        return List.of("10:00", "13:30", "17:00", "20:30", "23:30");
    }

    // Method to retrieve occupied seats for a specific movie, date, and showtime
    public List<String> getOccupiedSeats(Long movieId, String date, String showtime) {
        return seatingService.findOccupiedSeats(movieId, date, showtime);
    }

    // Method to book seats for a specific movie, date, showtime
    public void bookSeats(Long movieId, String date, String showtime, List<String> seatNumbers) {
        seatingService.bookSeats(movieId, date, showtime, seatNumbers);
    }

}
