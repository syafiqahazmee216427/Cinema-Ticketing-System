package com.csc3402.lab.ticketingsystem.repository;

import com.csc3402.lab.ticketingsystem.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
