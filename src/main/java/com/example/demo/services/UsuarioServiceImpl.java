package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.RolRepository;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    
    @Override
    public Usuario RegistrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }

    @Override
    public Usuario guardarUsuarioConRol(Usuario usuario, Integer idRol){
        Rol rol = rolRepository.findById(idRol).orElseThrow();

        usuario.setId_rol(rol);
        return usuarioRepository.save(usuario);
    }



    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuario(Long id) {
          return usuarioRepository.findById(id);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> validacionLogin(String correo, String contrasenia){
        return usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia);
    }

}
