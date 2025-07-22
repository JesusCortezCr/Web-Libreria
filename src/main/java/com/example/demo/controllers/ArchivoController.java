package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entities.Archivo;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.ArchivoRepository;
import com.example.demo.repositories.UsuarioRepository;

@Controller
public class ArchivoController {
 @Autowired
    private ArchivoRepository archivoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/archivos")
    public String listarArchivos(Model model, Principal principal) {
        String correo = principal.getName();
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            List<Archivo> archivos = archivoRepository.findByUsuario(usuario);
            model.addAttribute("archivos", archivos);
        } else {
            model.addAttribute("error", "No se pudo cargar los archivos.");
        }

        return "features/archivos/archivos";
    }

}
