package com.johann.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // * FORWARD & REDIRECT
    @GetMapping({ "", "/", "/home" })
    public String home() {
        // return "forrward:/api/users";
        return "redirect:/api/users";
    }

}
