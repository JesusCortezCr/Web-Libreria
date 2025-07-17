package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpSession;
import com.example.demo.services.UsuarioService;
import com.example.demo.services.RolService;

@Controller
public class UsuarioController {
    

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;


    @GetMapping("/login")
    public String mostrarLogin() {
        return "loginPagina";
    }
    

    @PostMapping("/login")
    public String login(@RequestParam String correo,
    @RequestParam String contrasenia,
    HttpSession session,
    Model model) {

      Optional<Usuario> usuarioOpt = usuarioService.validacionLogin(correo, contrasenia);


       if (usuarioOpt.isPresent()) {
        session.setAttribute("usuarioLogueado", usuarioOpt.get());
        return "redirect:/";
       } else {
        model.addAttribute("error", "Credenciales incorrectas");
        return "loginpagina";
       }
    }

     @GetMapping("/usuarios")
    public String mostrarUsuarios(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("roles", rolService.listarRoles());
        return "usuarios";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario, @RequestParam("id_rol") Integer idRol){
        usuarioService.guardarUsuarioConRol(usuario, idRol);
        return "redirect:/usuarios";
    
    }

   @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormulario(@PathVariable("id") Long id, Model model) {
    Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuario(id);
    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();
        usuario.setContrasenia(""); // limpiamos el hash
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("roles", rolService.listarRoles());
        return "usuarios"; // usas el mismo HTML de lista y edición
    } else {
        return "redirect:/usuarios";
    }
}


    @PostMapping("/usuario_actualizar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario, @RequestParam("id_rol") Integer idRol) {
    usuarioService.guardarUsuarioConRol(usuario, idRol);
    return "redirect:/usuarios";
}


    @GetMapping("usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
    

    

}
