package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @GetMapping("/inicio-sesion")
    public String mostrarLogin() {
        return "features/usuarios/login";
    }

}
