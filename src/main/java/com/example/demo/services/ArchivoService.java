package com.example.demo.services;

import java.util.List;



import com.example.demo.entities.Archivo;
import com.example.demo.entities.Usuario;


public interface ArchivoService {

   

    List<Archivo> listaArchivos();
    void guardarArchivo(Archivo archivo);

    List<Archivo> obtenerTodosConComentariosYUsuarios();
    
    List<Archivo> listarArchivosPorUsuario(Usuario usuario);
    
    void eliminarArchivo(Long id);
}

