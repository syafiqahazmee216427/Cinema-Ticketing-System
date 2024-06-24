package com.csc3402.lab.ticketingsystem.service;

import com.csc3402.lab.ticketingsystem.model.Hall;
import com.csc3402.lab.ticketingsystem.model.Ticket;
import com.csc3402.lab.ticketingsystem.repository.HallRepository;
import com.csc3402.lab.ticketingsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    // Method to retrieve all halls from the database
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

}
