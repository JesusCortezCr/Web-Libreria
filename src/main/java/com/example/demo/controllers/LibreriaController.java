package com.example.demo.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.entities.Contacto;
import com.example.demo.services.ContactoService;
import com.example.demo.services.RolService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.UsuarioService;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;

@Controller
public class LibreriaController {

    @Autowired
    private ContactoService contactoService;

    // @GetMapping({"/login","/"})
    // public String mostrarLogin(){
    // return "login";
    // }
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    


    @GetMapping({ "/", "" })
public String redirigirInicio(HttpSession session) {
    if (session.getAttribute("usuarioLogueado") == null) {
        return "redirect:/login"; // o "redirect:/registro" si quieres que vaya directo al registro
    } else {
        return "redirect:/pagina-principal"; // Esta sería tu vista real con libros
    }
}

    @GetMapping("/pagina-principal")
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
    public String contacto(Model model) {
        Contacto contacto = new Contacto();
        model.addAttribute("contacto", contacto);
        return "Contactanos";
    }

    @PostMapping("/guardar-contacto")
    public String guardarContacto(Model model, @ModelAttribute Contacto contacto2) {
        contactoService.guardarContacto(contacto2);
        Contacto contacto = new Contacto();
        model.addAttribute("contacto", contacto);
        return "redirect:/";
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

    @GetMapping("/mostrarFormularioObras")
    public String mostrarRegistroObras() {
        return "registroObras";
    }

   
}

    