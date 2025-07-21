package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticulosController {

    @GetMapping("/articulos")
    public String mostrarVistaArticulos() {
        
        return "pages/articulos-interfaz";
    }
     
}
