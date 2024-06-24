package com.csc3402.lab.ticketingsystem.service;

import com.csc3402.lab.ticketingsystem.model.Customer;
import com.csc3402.lab.ticketingsystem.model.Hall;
import com.csc3402.lab.ticketingsystem.model.Movie;
import com.csc3402.lab.ticketingsystem.model.Ticket;
import com.csc3402.lab.ticketingsystem.repository.CustomerRepository;
import com.csc3402.lab.ticketingsystem.repository.HallRepository;
import com.csc3402.lab.ticketingsystem.repository.MovieRepository;
import com.csc3402.lab.ticketingsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private SeatingService seatingService;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    // Method to book a ticket for a movie screening
    public Ticket bookTicket(Long movieId, Customer customer, String date, String showtime, List<String> seatNumbers) {
        // Save customer if it's a new customer
        if (customer.getCustId() == null) {
            customerRepository.save(customer);
        }

        // Fetch movie by id
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            throw new RuntimeException("Movie not found with id: " + movieId);
        }

        // Fetch hall associated with the movie
        Hall hall = hallRepository.findByMovieId(movieId);
        if (hall == null) {
            throw new RuntimeException("Hall not found for movie id: " + movieId);
        }

        // Convert date and showtime to appropriate types (LocalDate and LocalTime)
        LocalDate screeningDate = LocalDate.parse(date, dateFormatter);
        LocalTime screeningTime = LocalTime.parse(showtime, timeFormatter);

        // Create a single ticket with multiple seat numbers
        Ticket ticket = new Ticket();
        ticket.setMovie(movie);
        ticket.setCustomer(customer);
        ticket.setDateScreening(screeningDate);
        ticket.setShowtime(screeningTime);
        ticket.setSeatNumbers(seatNumbers);
        ticket.setHall(hall);

        // Save ticket to repository
        return ticketRepository.save(ticket);
    }


    // Method to retrieve all tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Method to retrieve a ticket by its ID
    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));
    }

    // Method to update customer's name on a ticket
    public Ticket updateCustomerName(Long ticketId, String newName) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket != null) {
            ticket.getCustomer().setName(newName);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    // Method to cancel a ticket
    public boolean cancelTicket(Long ticketId) {
        try {
            Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
            if (ticket != null) {
                // Release the seats before deleting the ticket
                seatingService.releaseSeats(ticket.getMovie().getMovieId(),
                        ticket.getDateScreening().format(dateFormatter),
                        ticket.getShowtime().format(timeFormatter),
                        ticket.getSeatNumbers());

                // Remove the association with the customer
                Customer customer = ticket.getCustomer();
                if (customer != null) {
                    customer.getTickets().remove(ticket);
                    if (customer.getTickets().isEmpty()) {
                        // If customer has no more tickets, delete the customer
                        customerRepository.delete(customer);
                    } else {
                        // Save customer to update the association
                        customerRepository.save(customer);
                    }
                }

                ticketRepository.delete(ticket);
                logger.info("Ticket with ID {} has been successfully cancelled.", ticketId);
                return true;
            } else {
                logger.warn("Ticket with ID {} not found.", ticketId);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error occurred while cancelling the ticket with ID {}: {}", ticketId, e.getMessage());
            return false;
        }
    }
}
