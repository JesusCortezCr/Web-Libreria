package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @GetMapping("/login")
    public String mostrarLogin() {
        return "loginPagina";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "RegistroUsuario";
    }
    
    
}
