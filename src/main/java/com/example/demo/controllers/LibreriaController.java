package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibreriaController {

    @GetMapping({"/login","/"})
    public String mostrarLogin(){
        return "login";
    }
}
