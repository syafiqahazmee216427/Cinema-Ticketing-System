package com.csc3402.lab.ticketingsystem.repository;

import com.csc3402.lab.ticketingsystem.model.Seating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatingRepository extends JpaRepository<Seating, Long> {

    // Custom query method to find occupied seats for a specific movie, date, and showtime
    @Query("SELECT sb.seatNumber FROM Seating sb WHERE sb.movieId = :movieId AND sb.date = :date AND sb.showtime = :showtime")
    List<String> findOccupiedSeats(@Param("movieId") Long movieId, @Param("date") String date, @Param("showtime") String showtime);

    // Custom query method to find Seating entity by movieId, date, showtime, and seatNumber
    @Query("SELECT sb FROM Seating sb WHERE sb.movieId = :movieId AND sb.date = :date AND sb.showtime = :showtime AND sb.seatNumber = :seatNumber")
    Seating findSeating(@Param("movieId") Long movieId, @Param("date") String date, @Param("showtime") String showtime, @Param("seatNumber") String seatNumber);
}
