////package com.csc3402.lab.ticketingsystem.demo;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.PostMapping;
////
////@Controller
////public class UserController {
////    @Autowired
////    private UserService userService;
////
////    @GetMapping("/registration")
////    public String showRegistrationForm(Model model) {
////        model.addAttribute("user", new User());
////        return "registration";
////    }
////
////    @PostMapping("/registration")
////    public String registerUser(@ModelAttribute("user") User user) {
////        userService.saveUser(user);
////        return "redirect:/login";
////    }
////
////    @GetMapping("/login")
////    public String showLoginForm() {
////        return "login";
////    }
////
////    @PostMapping("/login")
////    public String loginUser(@ModelAttribute("user") User user, Model model) {
////        User existingUser = userService.findByEmail(user.getEmail());
////        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
////            // Redirect to a welcome page or home page
////            return "redirect:/cinema/";
////        } else {
////            // Return to login with error message
////            model.addAttribute("error", "Invalid email or password");
////            return "login";
////        }
////    }
////
////}
//package com.csc3402.lab.ticketingsystem.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/registration")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String registerUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/login";
//    }
//
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login";
//    }
//}
package com.csc3402.lab.ticketingsystem.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}

