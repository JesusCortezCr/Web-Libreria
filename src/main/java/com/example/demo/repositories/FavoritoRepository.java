package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entities.Favorito;
import com.example.demo.entities.Usuario;


public interface FavoritoRepository  extends JpaRepository<Favorito, Long>{
    boolean existsByArchivoIdAndUsuarioId(Long archivoId, Long usuarioId);
    Favorito findByArchivoIdAndUsuarioId(Long archivoId, Long usuarioId);
    List<Favorito> findAllByUsuario(Usuario usuario);
    List<Favorito> findByUsuario(Usuario usuario);
    List<Favorito> findByUsuarioId(Long usuarioId);
     boolean existsByUsuarioIdAndArchivoId(Long usuarioId, Long archivoId);
     void deleteByUsuarioIdAndArchivoId(Long usuarioId, Long archivoId);
}
