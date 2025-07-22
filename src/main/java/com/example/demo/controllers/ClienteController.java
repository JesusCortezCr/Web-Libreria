package com.example.demo.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource; 
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Archivo;
import com.example.demo.entities.Contacto;
import com.example.demo.entities.TipoArchivo;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.ArchivoRepository;
import com.example.demo.repositories.TipoArchivoRepository;
import com.example.demo.repositories.UsuarioRepository;

import jakarta.validation.Valid;

import com.example.demo.services.ArchivoService;
import com.example.demo.services.FileService;
import com.example.demo.services.UsuarioService;
import org.springframework.security.web.csrf.CsrfToken;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private TipoArchivoRepository tipoArchivoRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ArchivoRepository archivoRepository;

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


    @GetMapping("mostrar-publicar-archivos")
    public String mostrarPublicarArchivos(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            model.addAttribute("archivo", new Archivo());
            model.addAttribute("tiposArchivo", tipoArchivoRepository.findAll());
        }
        return "features/archivos/publicar-archivos";
    }

    @PostMapping("publicar-archivo")
    public String publicarArchivo(@ModelAttribute("archivo") Archivo archivo,
                              @RequestParam("portadaFile") MultipartFile portadaFile,
                              @RequestParam("archivoFile") MultipartFile archivoFile,
                              BindingResult result,
                              Model model,
                              Principal principal) {

    if (principal == null) {
        model.addAttribute("error", "Debes iniciar sesión para publicar un archivo.");
        return "features/archivos/publicar-archivos";
    }

    Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    model.addAttribute("usuario", usuario);
    model.addAttribute("tiposArchivo", tipoArchivoRepository.findAll());

    if (result.hasErrors()) {
        model.addAttribute("error", "Hay errores en el formulario.");
        return "features/archivos/publicar-archivos";
    }

    try {
        // Guardar portada
        String rutaPortada = fileService.guardarArchivo(portadaFile);
        if (rutaPortada == null || rutaPortada.isEmpty()) {
            model.addAttribute("error", "No se pudo guardar la imagen de portada.");
            return "features/archivos/publicar-archivos";
        }

        // Guardar archivo PDF
        String rutaPdf = fileService.guardarArchivo(archivoFile);
        if (rutaPdf == null || rutaPdf.isEmpty()) {
            model.addAttribute("error", "No se pudo guardar el archivo PDF.");
            return "features/archivos/publicar-archivos";
        }

        // Configurar archivo
        archivo.setPortada(rutaPortada);
        archivo.setArchivo_pdf(rutaPdf);
        archivo.setUsuario(usuario);
        archivo.setFechaSubida(LocalDate.now());

        // Guardar en la base de datos
        archivoService.guardarArchivo(archivo);

        model.addAttribute("success", "Publicación exitosa!");
        model.addAttribute("archivo", new Archivo()); // Limpiar el formulario

    } catch (IOException e) {
        model.addAttribute("error", "Error al guardar los archivos: " + e.getMessage());
    }

    return "features/archivos/publicar-archivos";
    }

    // mostrar pagina mis archivos
    @GetMapping("mostrar-mis-archivos")
    public String mostrarPaginaMisArchivos(Model model, Principal principal, HttpServletRequest request) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            model.addAttribute("archivos", archivoService.listarArchivosPorUsuario(usuario));
        }

        // Agregamos manualmente el token CSRF
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("_csrf", csrfToken);

        return "features/archivos/mis-archivos";
    }

    //eliminar mis archivos
    @PostMapping("/archivo-eliminar/{id}")
        public String eliminarArchivo(@PathVariable Long id, Principal principal, Model model) {
            try {
             archivoService.eliminarArchivo(id);
             return "redirect:/cliente/mostrar-mis-archivos?success";
            } catch (Exception e) {
             return "redirect:/cliente/mostrar-mis-archivos?error";
            }
        }

    //para devolver el json    
    @GetMapping("/archivo/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> obtenerArchivoPorId(@PathVariable Long id) {
    Archivo archivo = archivoRepository.findById(id).orElse(null);
    if (archivo == null) return ResponseEntity.notFound().build();

    Map<String, Object> data = new HashMap<>();
    data.put("id", archivo.getId());
    data.put("titulo", archivo.getTitulo());
    data.put("descripcion", archivo.getDescripcion());
    data.put("fechaSubida", archivo.getFechaSubida().toString()); // ISO format
    data.put("tipoArchivoId", archivo.getTipoArchivo().getId());

    return ResponseEntity.ok(data);
    } 
    
    //end point para guardar los cambios
    @PostMapping("/archivo-editar")
    public String guardarEdicion(
    @RequestParam Long id,
    @RequestParam String titulo,
    @RequestParam String descripcion,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSubida,
    @RequestParam("tipoArchivoId") Integer tipoArchivoId,
    @RequestParam(value = "portadaFile", required = false) MultipartFile portadaFile,
    @RequestParam(value = "archivoFile", required = false) MultipartFile archivoFile
    ) throws IOException {
    Archivo archivo = archivoRepository.findById(id).orElseThrow();

    archivo.setTitulo(titulo);
    archivo.setDescripcion(descripcion);
    archivo.setFechaSubida(fechaSubida);

    archivo.setTipoArchivo(tipoArchivoRepository.findById(tipoArchivoId).orElse(null));

     Path uploadPath = Paths.get("uploads");
    if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
    } 
    // Si subieron una nueva portada
    if (portadaFile != null && !portadaFile.isEmpty()) {
        String nombrePortada = UUID.randomUUID().toString() + "_" + portadaFile.getOriginalFilename();
        Path rutaPortada = Paths.get("uploads").resolve(nombrePortada);
        Files.copy(portadaFile.getInputStream(), rutaPortada);
        archivo.setPortada("/uploads/" + nombrePortada);
    }

    // Si subieron un nuevo PDF
    if (archivoFile != null && !archivoFile.isEmpty()) {
        String nombrePdf = UUID.randomUUID().toString() + "_" + archivoFile.getOriginalFilename();
        Path rutaPdf = Paths.get("uploads").resolve(nombrePdf);
        Files.copy(archivoFile.getInputStream(), rutaPdf);
        archivo.setArchivo_pdf("/uploads/" + nombrePdf);
    }

    archivoRepository.save(archivo);
    return "redirect:/cliente/mostrar-mis-archivos";
    }
    //cargar modal
    @GetMapping("/archivo/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
    Archivo archivo = archivoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Archivo no encontrado"));
    List<TipoArchivo> tipos = tipoArchivoRepository.findAll();

    model.addAttribute("archivo", archivo);
    model.addAttribute("tipos", tipos);

    return "archivo/formulario_edicion"; // Asegúrate de que este es el nombre del HTML del formulario
    }

    


    // mostrar pagina contactanos
    @GetMapping("mostrar-contactanos")
    public String mostrarPaginaContactanos(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            model.addAttribute("contacto", new Contacto());
  
        }
        return "features/contactanos/contacto-form";
    }

    // mostrar pagina nosotros
    @GetMapping("mostrar-nosotros")
    public String mostrarPaginaNosotros(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByCorreo(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
        }
        return "pages/sobre-nosotros";
    }

    // mostrar pagina archivos
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

    //  mostrar imagen respecto al nombre del archivo
    @GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> mostrarArchivo(@PathVariable String filename) {
    try {
        Path uploadPath = Paths.get("uploads").resolve(filename).normalize();
        Resource resource = new UrlResource(uploadPath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(uploadPath);

        if (contentType == null || contentType.equals("application/octet-stream")) {
            // Estimar manualmente por extensión
            String lower = filename.toLowerCase();
            if (lower.endsWith(".png")) {
                contentType = "image/png";
            } else if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) {
                contentType = "image/jpeg";
            } else if (lower.endsWith(".gif")) {
                contentType = "image/gif";
            } else if (lower.endsWith(".pdf")) {
                contentType = "application/pdf";
            } else {
                contentType = "application/octet-stream";
            }
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);

    } catch (MalformedURLException e) {
        return ResponseEntity.badRequest().build();
    } catch (IOException e) {
        return ResponseEntity.internalServerError().build();
    }
    }

    
    // mostrar pagina favoritos
    @GetMapping("listaa-favoritos")
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
