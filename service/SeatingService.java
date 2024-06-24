package com.csc3402.lab.ticketingsystem.service;

import com.csc3402.lab.ticketingsystem.model.Seating;
import com.csc3402.lab.ticketingsystem.repository.SeatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingService {

    @Autowired
    private SeatingRepository seatingRepository;

    // Find occupied seats
    public List<String> findOccupiedSeats(Long movieId, String date, String showtime) {
        return seatingRepository.findOccupiedSeats(movieId, date, showtime);
    }

    // Book seats
    public void bookSeats(Long movieId, String date, String showtime, List<String> seatNumbers) {
        for (String seatNumber : seatNumbers) {
            if (!isSeatOccupied(movieId, date, showtime, seatNumber)) {
                Seating seating = new Seating();
                seating.setMovieId(movieId);
                seating.setDate(date);
                seating.setShowtime(showtime);
                seating.setSeatNumber(seatNumber);
                seatingRepository.save(seating);
            } else {
                // Handle case where seat is already occupied
                throw new IllegalArgumentException("Seat " + seatNumber + " is already occupied.");
            }
        }
    }

    // Check if a seat is occupied
    private boolean isSeatOccupied(Long movieId, String date, String showtime, String seatNumber) {
        List<String> occupiedSeats = seatingRepository.findOccupiedSeats(movieId, date, showtime);
        return occupiedSeats.contains(seatNumber);
    }

    // Release seats
    public void releaseSeats(Long movieId, String date, String showtime, List<String> seatNumbers) {
        for (String seatNumber : seatNumbers) {
            Seating seating = seatingRepository.findSeating(movieId, date, showtime, seatNumber);
            if (seating != null) {
                seatingRepository.delete(seating);
            }
        }
    }
}
