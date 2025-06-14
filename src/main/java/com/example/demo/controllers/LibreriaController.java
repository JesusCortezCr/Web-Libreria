package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;


@Controller
public class LibreriaController {

    // @GetMapping({"/login","/"})
    // public String mostrarLogin(){
    // return "login";
    // }
    @Autowired
    private UsuarioService usuarioService;

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

    @GetMapping("/contactanos")
    public String contacto(){
        return "Contactanos";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "loginPagina";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        Usuario users = new Usuario();
        model.addAttribute("usuario",users);
        return "RegistroUsuario";
    }
    
    @PostMapping("/registro_guardado")
    public String postMethodName(@ModelAttribute Usuario usuariofijo) {
       usuarioService.RegistrarUsuario(usuariofijo);
        return "redirect:/";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario, @RequestParam("id_rol") Integer idRol){
        usuarioService.guardarUsuarioConRol(usuario, idRol);
        return "redirect:/usuarios";
    
    }

    
    
    
   


    


}
