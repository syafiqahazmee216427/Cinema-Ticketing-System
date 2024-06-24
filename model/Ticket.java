package com.csc3402.lab.ticketingsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private LocalDate dateScreening;
    private LocalTime showtime;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @ElementCollection
    @CollectionTable(name = "ticket_seat", joinColumns = @JoinColumn(name = "ticket_id"))
    @Column(name = "seat_number")
    private List<String> seatNumbers;

    public Ticket() {}

    public Ticket(LocalDate dateScreening, LocalTime showtime, List<String> seatNumbers) {
        this.dateScreening = dateScreening;
        this.showtime = showtime;
        this.seatNumbers = seatNumbers;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDate getDateScreening() {
        return dateScreening;
    }

    public void setDateScreening(LocalDate dateScreening) {
        this.dateScreening = dateScreening;
    }

    public LocalTime getShowtime() {
        return showtime;
    }

    public void setShowtime(LocalTime showtime) {
        this.showtime = showtime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", dateScreening=" + dateScreening +
                ", showtime=" + showtime +
                ", seatNumbers=" + seatNumbers +
                '}';
    }
}
