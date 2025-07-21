package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NosotrosController {

    @GetMapping("/sobre-nosotros")
    public String mostrarSobreNosotros() {
        
        return "pages/sobre-nosotros";
    }
     

}
