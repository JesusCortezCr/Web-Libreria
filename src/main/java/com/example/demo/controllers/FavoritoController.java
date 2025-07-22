package com.example.demo.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.entities.Archivo;
import com.example.demo.entities.Favorito;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.ArchivoRepository;
import com.example.demo.repositories.FavoritoRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.FavoritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ArchivoRepository archivoRepository; 

    @GetMapping("/cliente/mostrar-favoritos")
    public String mostrarFavoritos(Model model, Principal principal) {
    Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(principal.getName());

    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();

        List<Favorito> favoritos = favoritoRepository.findByUsuarioId(usuario.getId());

        model.addAttribute("favoritos", favoritos); 
    } else {
        model.addAttribute("favoritos", Collections.emptyList());
    }

    return "features/archivos/mis-favoritos";
    }   

    @PostMapping("/favorito/agregar/{id}")
    public String agregarAFavoritos(@PathVariable("id") Long archivoId, Principal principal) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(principal.getName());

        if (usuarioOpt.isPresent()) {
            favoritoService.agregarFavorito(archivoId, usuarioOpt.get().getId());
        }

        return "redirect:/cliente/mostrar-favoritos";
    }

    @PostMapping("/favorito/eliminar/{id}")
public String eliminarFavorito(@PathVariable("id") Long archivoId, Principal principal, RedirectAttributes redirectAttrs) {
    Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(principal.getName());

    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();
        favoritoService.eliminarFavorito(usuario.getId(), archivoId);
        redirectAttrs.addFlashAttribute("success", "Eliminado de favoritos.");
    } else {
        redirectAttrs.addFlashAttribute("error", "Usuario no encontrado.");
    }

    return "redirect:/cliente/mostrar-favoritos";
}
}
