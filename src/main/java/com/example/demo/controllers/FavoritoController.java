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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        if (principal == null) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                .orElse(null);

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("favoritos", favoritoService.obtenerFavoritos(usuario));
        model.addAttribute("usuario", usuario);

        return "features/archivos/mis-favoritos";
    }

    @PostMapping("/favorito/agregar/{id}")
    public String agregarAFavoritos(@PathVariable Long id,
            Principal principal,
            RedirectAttributes redirectAttr) {
        if (principal == null) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioRepository.findByCorreo(principal.getName()).orElse(null);
        if (usuario == null) {
            return "redirect:/login";
        }

        try {
            favoritoService.agregarFavorito(id, usuario.getId());
            redirectAttr.addFlashAttribute("success", "¡Archivo agregado a favoritos!");
        } catch (Exception e) {
            // Solo mostrar errores no esperados
            if (!e.getMessage().contains("no encontrado")) {
                redirectAttr.addFlashAttribute("error", e.getMessage());
            }
        }

        return "redirect:/cliente/mostrar-archivos";
    }

    @PostMapping("/favorito/eliminar/{id}")
    public String eliminarFavorito(@PathVariable Long id,
            Principal principal,
            RedirectAttributes redirectAttr) {
        if (principal == null) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioRepository.findByCorreo(principal.getName()).orElse(null);
        if (usuario == null) {
            return "redirect:/login";
        }

        favoritoService.eliminarFavorito(id, usuario.getId());
        redirectAttr.addFlashAttribute("success", "Archivo eliminado de favoritos");

        return "redirect:/cliente/mostrar-favoritos";
    }
}
