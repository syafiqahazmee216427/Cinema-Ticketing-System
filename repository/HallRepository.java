package com.csc3402.lab.ticketingsystem.repository;

import com.csc3402.lab.ticketingsystem.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    // Custom query method to find a Hall by Movie ID
    @Query("SELECT h FROM Hall h WHERE h.movie.movieId = :movieId")
    Hall findByMovieId(Long movieId);

}



