package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;

import jakarta.validation.Valid;
import com.example.demo.services.UsuarioService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("nuevo-cliente")
    public String mostrarNuevoCliente(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "features/clientes/registro-cliente";
    }

    @PostMapping("registrar-cliente")
    public String registroCliente(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "features/clientes/registro-cliente";
        }
        if (usuarioService.usuarioExiste(usuario.getCorreo())) {
            model.addAttribute("error", "El correo ya está registrado.");
            return "RegistroUsuario";
        }
        usuarioService.RegistrarCliente(usuario);

        return "redirect:/login?success";

    }

}
