package com.example.demo.services;

import java.util.List;



import com.example.demo.entities.Favorito;
import com.example.demo.entities.Usuario;


public interface FavoritoService {

   
    void agregarFavorito(Long archivoId, Long usuarioId);

    void eliminarFavorito(Long archivoId, Long usuarioId);

    boolean existeFavorito(Long archivoId, Long usuarioId);

    List<Favorito> listarFavoritosPorUsuario(Usuario usuarioId);

    List<Favorito> listarFavoritosPorUsuario(Long usuarioId);
    
}
