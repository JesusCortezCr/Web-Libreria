package com.example.demo.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Archivo;
import com.example.demo.repositories.ArchivoRepository;

@Controller
public class LibroController {

    @Autowired
    private ArchivoRepository archivoRepository;

    @GetMapping("/recomendados")
    public String mostrarLibrosRecomendados(Model model, Principal principal) {
    List<Archivo> archivos = archivoRepository.findAll();
    model.addAttribute("archivos", archivos);
    return "archivos"; 
    }

    @GetMapping("/imagen/{nombre:.+}")
    @ResponseBody
    public ResponseEntity<Resource> verImagen(@PathVariable String nombre) throws IOException {
    Path ruta = Paths.get("uploads").resolve(nombre).toAbsolutePath();

    Resource recurso = new UrlResource(ruta.toUri());
    if (!recurso.exists() || !recurso.isReadable()) {
        throw new RuntimeException("No se puede cargar la imagen: " + nombre);
    }

    String contentType = Files.probeContentType(ruta);
    if (contentType == null) {
        contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .body(recurso);
    }

    @GetMapping("/imagen/{nombre}")
@ResponseBody
public ResponseEntity<Resource> servirImagen(@PathVariable String nombre) throws MalformedURLException {
    Path ruta = Paths.get("uploads").resolve(nombre).toAbsolutePath();
    UrlResource recurso = new UrlResource(ruta.toUri());

    if (!recurso.exists() || !recurso.isReadable()) {
        throw new RuntimeException("No se puede cargar la imagen: " + nombre);
    }

    String contentType;
    try {
        contentType = Files.probeContentType(ruta);
        if (contentType == null) {
            contentType = "application/octet-stream"; // o "image/png" si sabes que son solo imágenes
        }
    } catch (Exception e) {
        contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType)
            .body(recurso);
}

}
