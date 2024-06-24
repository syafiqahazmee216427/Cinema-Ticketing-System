package com.csc3402.lab.ticketingsystem.service;

import com.csc3402.lab.ticketingsystem.model.Customer;
import com.csc3402.lab.ticketingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
