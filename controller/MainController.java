package com.csc3402.lab.ticketingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cinema")
public class MainController {

    @GetMapping("/")
    public String showMainPage() {
        return "main";
    }
}
