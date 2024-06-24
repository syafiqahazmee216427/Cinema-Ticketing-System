package com.csc3402.lab.ticketingsystem.controller;

import com.csc3402.lab.ticketingsystem.model.Customer;
import com.csc3402.lab.ticketingsystem.model.Movie;
import com.csc3402.lab.ticketingsystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Show all movies
    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movie-list";
    }

    // Retrieving movie details
    @GetMapping("/movies/{id}")
    public String getMovieDetails(@PathVariable Long id,
                                  @RequestParam(required = false) String date,
                                  @RequestParam(required = false) String showtime,
                                  @ModelAttribute Customer customer,
                                  Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            model.addAttribute("movie", movie);
            model.addAttribute("customer", customer);


            if (date == null || showtime == null) {
                List<String> availableDates = movieService.getAvailableDates(id);
                List<String> availableShowtimes = movieService.getAvailableShowtimes(id);


                model.addAttribute("availableDates", availableDates);
                model.addAttribute("availableShowtimes", availableShowtimes);
                model.addAttribute("movieId", id);


                return "movie-details";
            } else {
                model.addAttribute("movieId", id);
                model.addAttribute("date", date);
                model.addAttribute("showtime", showtime);
                model.addAttribute("occupiedSeats", movieService.getOccupiedSeats(id, date, showtime));
                return "seating";
            }
        }
        return "redirect:/movies";
    }

    // Handler for selecting seats for a specific movie and showtime
    @PostMapping("/movies/{id}/select-seats")
    public String showSeatSelection(@PathVariable("id") Long movieId,
                                    @RequestParam("date") String date,
                                    @RequestParam("showtime") String showtime,
                                    @RequestParam(required = false) List<String> seatNumber,
                                    Model model) {
        model.addAttribute("movieId", movieId);
        model.addAttribute("date", date);
        model.addAttribute("showtime", showtime);
        model.addAttribute("seatNumber", seatNumber != null ? seatNumber : new ArrayList<>());
        model.addAttribute("occupiedSeats", movieService.getOccupiedSeats(movieId, date, showtime));
        return "seating";
    }

    // Handler for displaying the about page
    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    // Handler for displaying the contact page
    @GetMapping("/contact")
    public String showContactPage() {
        return "contactus";
    }
}
