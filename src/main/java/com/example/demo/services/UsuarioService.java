package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Usuario;

public interface UsuarioService   {

     Usuario RegistrarCliente(Usuario usuario);
     List<Usuario> listarUsuarios();
     boolean usuarioExiste(String email);
     Optional<Usuario> obtenerUsuario(Long id);

     List<Usuario> obtenerTodosUsuarios();

     void eliminarUsuario(Long id);
    
     Optional<Usuario> validacionLogin(String correo, String contrasenia);
     
}
