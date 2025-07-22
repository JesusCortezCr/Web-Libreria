package com.example.demo.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Archivo;
import com.example.demo.entities.Usuario;

public interface ArchivoRepository extends JpaRepository<Archivo,Long> {

    List<Archivo> findByUsuario(Usuario usuario);
    
    @Query("SELECT a FROM Archivo a LEFT JOIN FETCH a.usuario LEFT JOIN FETCH a.comentarios")
    
    List<Archivo> findAllWithComentariosAndUsuario();

    
}
