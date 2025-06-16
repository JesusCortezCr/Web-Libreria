package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;
import org.springframework.ui.Model;

public class LoginController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String contrasenia, Model model){

        Optional<Usuario> usuario1 = usuarioService.validacionLogin(correo, contrasenia);

        if (usuario1.isPresent()) {
            model.addAttribute("nombre", usuario1.get().getNombre());
            return "Bienvenido";
        }else{
            model.addAttribute("Error", "Datos incorrectos");
            return"login";
        }   
    }

}
