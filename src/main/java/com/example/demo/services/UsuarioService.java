package com.example.demo.services;

import com.example.demo.entities.Usuario;

public interface UsuarioService   {

     Usuario RegistrarUsuario(Usuario usuario); 
    
     Usuario guardarUsuarioConRol(Usuario usuario, Integer idRol);
    

}
