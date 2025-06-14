package com.example.demo.services;

import com.example.demo.entities.Rol;
import com.example.demo.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService{

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}
