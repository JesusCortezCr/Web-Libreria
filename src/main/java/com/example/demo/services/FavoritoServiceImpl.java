package com.example.demo.services;


import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Archivo;
import com.example.demo.entities.Favorito;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.ArchivoRepository;
import com.example.demo.repositories.FavoritoRepository;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ArchivoRepository archivoRepository;

    @Override
    public void agregarFavorito(Long archivoId, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Archivo archivo = archivoRepository.findById(archivoId).orElse(null);

        if (usuario != null && archivo != null) {
            Favorito favorito = new Favorito();
            favorito.setUsuario(usuario); 
            favorito.setArchivo(archivo); 
            favoritoRepository.save(favorito);
        }
    }

    @Override
    public void eliminarFavorito(Long archivoId, Long usuarioId) {
        Favorito favorito = favoritoRepository.findByArchivoIdAndUsuarioId(archivoId, usuarioId);
        if (favorito != null) {
            favoritoRepository.delete(favorito);
        }
    }

    @Override
    public boolean existeFavorito(Long archivoId, Long usuarioId) {
        return favoritoRepository.existsByArchivoIdAndUsuarioId(archivoId, usuarioId);
    }

    @Override
    public List<Favorito> listarFavoritosPorUsuario(Long usuarioId) {
    Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
    if (usuario != null) {
        return favoritoRepository.findByUsuario(usuario);
    } else {
        return Collections.emptyList();
    }
    }

    @Override
    public List<Favorito> listarFavoritosPorUsuario(Usuario usuario) {
    if (usuario != null) {
        return favoritoRepository.findByUsuario(usuario);
    } else {
        return Collections.emptyList();
    }
    }
}