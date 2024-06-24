package com.csc3402.lab.ticketingsystem.controller;

import com.csc3402.lab.ticketingsystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private MovieService movieService;

    // Processing customer details and booking tickets
    @PostMapping("/movies/{id}/cust-details")
    public String showCustomerDetails(@PathVariable("id") Long movieId,
                                      @RequestParam("date") String date,
                                      @RequestParam("showtime") String showtime,
                                      @RequestParam("selectedSeats") String selectedSeats,
                                      Model model) {
        List<String> seatNumber = Arrays.asList(selectedSeats.split(","));
        int numberOfTickets = seatNumber.size();
        int pricePerTicket = 18; // Price per ticket
        int totalPrice = numberOfTickets * pricePerTicket;

        try {
            // Check if selected seats are already occupied
            List<String> occupiedSeats = movieService.getOccupiedSeats(movieId, date, showtime);
            for (String seat : seatNumber) {
                if (occupiedSeats.contains(seat)) {
                    throw new IllegalArgumentException("Seat " + seat + " is already occupied.");
                }
            }

            model.addAttribute("movieId", movieId);
            model.addAttribute("date", date);
            model.addAttribute("showtime", showtime);
            model.addAttribute("seatNumber", seatNumber);
            model.addAttribute("totalPrice", totalPrice);

            // Update seats as occupied
            movieService.bookSeats(movieId, date, showtime, seatNumber);

            return "cust-details";
        } catch (IllegalArgumentException e) {
            // Handle case where seat is already occupied
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("movieId", movieId);
            model.addAttribute("date", date);
            model.addAttribute("showtime", showtime);
            model.addAttribute("occupiedSeats", movieService.getOccupiedSeats(movieId, date, showtime));
            return "seating";
        }
    }
}
