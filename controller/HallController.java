package com.csc3402.lab.ticketingsystem.controller;

import com.csc3402.lab.ticketingsystem.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/halls")
public class HallController {

    @Autowired
    private HallService hallService;

    @GetMapping("/")
    public String listHalls(Model model) {
        model.addAttribute("halls", hallService.getAllHalls());
        return "hall-list";
    }
}
