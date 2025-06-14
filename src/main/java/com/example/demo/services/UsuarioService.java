package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Usuario;

public interface UsuarioService   {

     Usuario RegistrarUsuario(Usuario usuario); 
    
     Usuario guardarUsuarioConRol(Usuario usuario, Integer idRol);
    
     
     List<Usuario> listarUsuarios();

     Optional<Usuario> obtenerUsuario(Long id);

     List<Usuario> obtenerTodosUsuarios();

     void eliminarUsuario(Long id);

}
