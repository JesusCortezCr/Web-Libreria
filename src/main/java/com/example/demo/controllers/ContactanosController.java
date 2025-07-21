package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entities.Contacto;
import com.example.demo.repositories.ContactoRepository;

@Controller
public class ContactanosController {

    private final ContactoRepository contactoRepository;

    public ContactanosController(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    @GetMapping("/contactanos")
    public String mostrarFormularioContacto(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "features/contactanos/contacto-form";
    }

    @PostMapping("/guardar-contacto")
    public String guardarContacto(@ModelAttribute("contacto") Contacto contacto, Model model) {
        contactoRepository.save(contacto);
        model.addAttribute("mensajeExito", "¡Tu mensaje fue enviado con éxito!");
        model.addAttribute("contacto", new Contacto());
        return "features/contactanos/contacto-form"; 
    }
}
