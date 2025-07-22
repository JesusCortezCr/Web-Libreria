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
        // Verificar si ya existe el favorito sin lanzar excepción
        if (favoritoRepository.existsByUsuarioIdAndArchivoId(usuarioId, archivoId)) {
            return; // Silenciosamente salir si ya existe
        }

        // Usar referencias por ID sin cargar las entidades completas
        Favorito favorito = new Favorito();
        favorito.setUsuario(usuarioRepository.getReferenceById(usuarioId));
        favorito.setArchivo(archivoRepository.getReferenceById(archivoId));

        favoritoRepository.save(favorito);
    }

    @Override
    public void eliminarFavorito(Long archivoId, Long usuarioId) {
        favoritoRepository.deleteByUsuarioIdAndArchivoId(usuarioId, archivoId);
    }

    @Override
    public boolean existeFavorito(Long archivoId, Long usuarioId) {
        return favoritoRepository.existsByArchivoIdAndUsuarioId(archivoId, usuarioId);
    }

    @Override
    public List<Favorito> obtenerFavoritosPorUsuario(Long usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Favorito> obtenerFavoritos(Usuario usuario) {
        return favoritoRepository.findByUsuario(usuario);
    }
}