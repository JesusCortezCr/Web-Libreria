package com.example.demo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Archivo;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.ArchivoRepository;

@Service
public class ArchivoServiceImpl implements ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    private final Path RUTA_UPLOADS = Paths.get("uploads"); 

    @Override
    public List<Archivo> listaArchivos() {
        return archivoRepository.findAll();
    }

    @Override
    public void guardarArchivo(Archivo archivo) {
        archivoRepository.save(archivo);
    }

    @Override
    public List<Archivo> obtenerTodosConComentariosYUsuarios() {
        return archivoRepository.findAllWithComentariosAndUsuario();
    }

    @Override
    public List<Archivo> listarArchivosPorUsuario(Usuario usuario) {
        return archivoRepository.findByUsuario(usuario);
    }

    @Override
    public void eliminarArchivo(Long id) {
        Archivo archivo = archivoRepository.findById(id).orElse(null);

        if (archivo != null) {
            try {
                // Eliminar archivo PDF si existe
                if (archivo.getArchivo_pdf() != null) {
                    Path rutaPdf = RUTA_UPLOADS.resolve(Paths.get(archivo.getArchivo_pdf()).getFileName()).normalize();
                    Files.deleteIfExists(rutaPdf);
                }

                // Eliminar imagen de portada si existe
                if (archivo.getPortada() != null) {
                    Path rutaPortada = RUTA_UPLOADS.resolve(Paths.get(archivo.getPortada()).getFileName()).normalize();
                    Files.deleteIfExists(rutaPortada);
                }

            } catch (IOException e) {
                System.err.println("Error al eliminar archivos físicos del sistema: " + e.getMessage());
                // Podrías loguearlo también con un logger
            }

            // Finalmente, eliminar de la base de datos
            archivoRepository.deleteById(id);
        }
    }
}
