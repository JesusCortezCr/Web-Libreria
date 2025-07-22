package com.example.demo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        return "features/usuarios/login";
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            if ("ROLE_CLIENTE".equals(usuario.getRol().getNombre())) {
                return "pages/pagina-principal";
            }
            if (usuario.getRol() != null && "ROLE_ADMINISTRADOR".equals(usuario.getRol().getNombre())) {
                model.addAttribute("usuarios", usuarioService.traerUsuariosClientes());
                return "features/administrador/mantenimiento-clientes";
            }
        }
        return "pages/pagina-principal";
    }

}