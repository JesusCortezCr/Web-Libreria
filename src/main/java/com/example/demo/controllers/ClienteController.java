package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Archivo;
import com.example.demo.entities.Contacto;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;

import jakarta.validation.Valid;

import com.example.demo.services.ArchivoService;
import com.example.demo.services.UsuarioService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ArchivoService archivoService;

    // mostrar formulario de registra cliente
    @GetMapping("nuevo-cliente")
    public String mostrarNuevoCliente(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "features/clientes/registro-cliente";
    }

    // formulario que registra un nuevo cliente
    @PostMapping("registrar-cliente")
    public String registroCliente(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "features/clientes/registro-cliente";
        }
        if (usuarioService.usuarioExiste(usuario.getCorreo())) {
            model.addAttribute("error", "El correo ya está registrado.");
            return "features/clientes/registro-cliente";
        }
        try {
            usuarioService.RegistrarCliente(usuario);
            return "redirect:/login?succes";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "features/clientes/registro-cliente";
        }
    }

    // mostrar publicar archivos
    @GetMapping("mostrar-publicar-archivos")
    public String mostrarPublicarArchivos(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            model.addAttribute("archivo", new Archivo());
        }

        return "features/archivos/publicar-archivos";
    }

    // mostrar pagina mis archivos
    @GetMapping("mostrar-mis-archivos")
    public String mostrarPaginaMisArchivos(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            model.addAttribute("archivos", archivoService.listaArchivos());

        }
        return "features/archivos/mis-archivos";
    }

    //mostrar pagina contactanos
    @GetMapping("mostrar-contactanos")
    public String mostrarPaginaContactanos(Model model, Principal principal){
   if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            model.addAttribute("contacto", new Contacto());

        }
        return "features/contactanos/contacto-form";
    }

    //mostrar pagina nosotros
    @GetMapping("mostrar-nosotros")
    public String mostrarPaginaNosotros(Model model, Principal principal){
   if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
        }
        return "pages/sobre-nosotros";
    }

    // mostrar pagina  archivos
    @GetMapping("mostrar-archivos")
    public String mostrarPaginaArchivos(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            model.addAttribute("archivos", archivoService.listaArchivos());

        }
        return "features/archivos/archivos";
    }
    // mostrar pagina  favoritos
    @GetMapping("mostrar-favoritos")
    public String mostrarPaginaFavoritos(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            model.addAttribute("archivos", archivoService.listaArchivos());

        }
        return "features/archivos/mis-favoritos";
    }



}