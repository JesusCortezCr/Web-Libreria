package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.services.ArchivoService;
import com.example.demo.entities.Archivo;
import com.example.demo.entities.Usuario;


import jakarta.servlet.http.HttpSession;

@Controller
public class ArticulosController {

    @Autowired
    private ArchivoService archivoService;

     
    @GetMapping("/articulos")
    public String verArticulos(Model model, HttpSession session) {
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    List<Archivo> archivos = archivoService.obtenerTodosConComentariosYUsuarios();
    model.addAttribute("usuario", usuario);
    model.addAttribute("archivos", archivos);
    return "pages/articulos-interfaz";
}
}
