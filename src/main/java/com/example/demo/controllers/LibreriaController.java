package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class LibreriaController {

    // @GetMapping({"/login","/"})
    // public String mostrarLogin(){
    // return "login";
    // }
    @GetMapping({ "/", "" })
    public String paginaPrincipal(Model model) {

        List<Libro> libros = List.of(
                new Libro("Cien años de Soledad", "Gabriel Garcia Marquez", 1967, "417 pag.", "Libro"),
                new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "863 pag.", "Libro"),
                new Libro("Impacto del cambio Climático", "Juan Perez y Maria Lopez", 2023, "15 pag.",
                        "Investigaciones"),
                new Libro("Eficacia de la Inteligencia Artificial en el diagnóstico temprano del cáncer de mama",
                        "Ana Torres y Luis Martinez", 2024, "18 pag.", "Investigaciones"),
                new Libro("El susurro del Bosque", "Laura Méndez", 2024, "12 pag.", "Cuento"),
                new Libro("La sombra del tiempo", "Carlos Rivas", 2019, "15 pag.", "Cuento"),
                new Libro("Luz en la penumbra", "Monica Torres", 2022, "120 pag.", "Novela"),
                new Libro("El eco de los Sueños", "Javier Morales", 2022, "410 pag.", "Novela"));

        model.addAttribute("libros", libros);
        model.addAttribute("cantidadLibros", libros.size());
        return "PaginaPrincipal";
    }

    @GetMapping("/articulos")
    public String mostrarArticulosInterfaz() {
        return "ArticulosInterfaz";
    }

    @GetMapping("/sobre-nosotros")
    public String sobreNosotros() {
        return "SobreNosostros";
    }

  

}
