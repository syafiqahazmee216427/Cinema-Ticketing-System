package com.csc3402.lab.ticketingsystem.controller;

import com.csc3402.lab.ticketingsystem.model.Customer;
import com.csc3402.lab.ticketingsystem.model.Ticket;
import com.csc3402.lab.ticketingsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book")
    public String bookMovie(@RequestParam("movieId") Long movieId,
                            @ModelAttribute Customer customer,
                            @RequestParam String date,
                            @RequestParam String showtime,
                            @RequestParam("seatNumber") List<String> seatNumbers,
                            @RequestParam("paymentType") String paymentType,
                            Model model) {

        // Validate seatNumber (if needed)
        if (seatNumbers == null || seatNumbers.isEmpty()) {
            model.addAttribute("error", "No seats selected for booking.");
            return "seating";
        }

        // Book the ticket
        Ticket savedTicket = ticketService.bookTicket(movieId, customer, date, showtime, seatNumbers);

        // Check if ticket was saved successfully
        if (savedTicket == null) {
            // Handle scenario where ticket booking failed
            model.addAttribute("error", "Failed to book the ticket.");
            return "seating";
        }

        // Fetch all tickets including the newly purchased one
        List<Ticket> allTickets = ticketService.getAllTickets();

        // Add attributes to the model for rendering in the view
        model.addAttribute("ticket", savedTicket);
        model.addAttribute("allTickets", allTickets);
        model.addAttribute("paymentType", paymentType);

        // Return the view name for ticket details
        return "ticket-details";
    }

    @GetMapping("/ticket-details/{ticketId}")
    public String showTicketDetails(@PathVariable("ticketId") Long ticketId, Model model) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        model.addAttribute("ticket", ticket);

        // Fetch all tickets including the newly purchased one
        List<Ticket> allTickets = ticketService.getAllTickets();
        model.addAttribute("allTickets", allTickets);

        return "ticket-details";
    }

    // Show the update form
    @GetMapping("/update/{ticketId}")
    public String showUpdateForm(@PathVariable("ticketId") Long ticketId, Model model) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        model.addAttribute("ticket", ticket);
        return "update-customer-name";
    }

    // Process the update
    @PostMapping("/update/{ticketId}")
    public String updateCustomerName(@PathVariable("ticketId") Long ticketId,
                                     @RequestParam("newName") String newName,
                                     Model model) {
        Ticket updatedTicket = ticketService.updateCustomerName(ticketId, newName);
        if (updatedTicket == null) {
            model.addAttribute("error", "Failed to update the ticket.");
            return "update-customer-name";
        }
        model.addAttribute("ticket", updatedTicket);
        return "redirect:/tickets/ticket-details/" + ticketId;
    }

    // Cancel the ticket
    @PostMapping("/cancel/{ticketId}")
    public String cancelTicket(@PathVariable("ticketId") Long ticketId, RedirectAttributes redirectAttributes) {
        boolean isCancelled = ticketService.cancelTicket(ticketId);
        if (!isCancelled) {
            redirectAttributes.addFlashAttribute("error", "Failed to cancel the ticket. Please try again or contact support.");
            return "redirect:/tickets/ticket-details/" + ticketId;
        }
        redirectAttributes.addFlashAttribute("message", "Ticket has been cancelled successfully.");
        return "redirect:/tickets";
    }

    @GetMapping
    public String listAllTickets(Model model) {
        List<Ticket> allTickets = ticketService.getAllTickets();
        model.addAttribute("allTickets", allTickets);
        return "ticket-list"; // Ensure you have a Thymeleaf template named 'ticket-list.html'
    }

}
