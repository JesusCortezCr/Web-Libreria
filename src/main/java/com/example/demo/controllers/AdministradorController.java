package com.example.demo.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.RolRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepository;

    @GetMapping("/inicio-sesion")
    public String mostrarLogin() {
        return "features/usuarios/login";
    }

    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model, Principal principal) {
        Usuario usuarioActual = usuarioRepository.findByCorreo(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        model.addAttribute("usuario", usuarioActual);
        model.addAttribute("usuarioNuevo", new Usuario());
        return "features/administrador/form-usuario";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        Usuario usuarioActual = usuarioRepository.findByCorreo(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        model.addAttribute("usuario", usuarioActual);
        model.addAttribute("usuarios", usuarioService.traerUsuariosClientes());
        return "features/administrador/mantenimiento-clientes";
    }

    @PostMapping("/usuarios/nuevo")
    public String guardarUsuario(@Valid @ModelAttribute("usuarioNuevo") Usuario usuario,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/form-usuario";
        }
        Rol rol = rolRepository.findById(1L).get();
        usuario.setRol(rol);
        usuarioService.RegistrarCliente(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model, Principal principal) {
        Usuario usuarioEditar = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));
        Usuario usuarioActual = usuarioRepository.findByCorreo(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        model.addAttribute("usuario", usuarioActual);
        model.addAttribute("usuarioEditar", usuarioEditar);
        return "features/administrador/form-editar-usuario";
    }

    @PostMapping("/usuarios/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Long id,
            @Valid @ModelAttribute("usuarioEditar") Usuario usuario,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "features/administrador/form-editar-usuario";
        }

        usuario.setId(id);
        Rol rol = rolRepository.findById(1L).orElseThrow(); // puedes personalizarlo
        usuario.setRol(rol);
        usuarioRepository.save(usuario);

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        ;
        return "redirect:/admin/usuarios";
    }

}
