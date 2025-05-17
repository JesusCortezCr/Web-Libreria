package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibreriaController {

    @GetMapping({"/",""})
    public String paginaPrincipal() {

        return "PaginaPrincipal";
    }

    @GetMapping("/articulos")
    public String mostrarArticulosInterfaz(){
        
        return "ArticulosInterfaz";
    }

    @GetMapping("/sobre-nosotros")
    public String sobreNosotros(){
        return "SobreNosostros";
    }
}
