package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Archivo;
import com.example.demo.repositories.ArchivoRepository;

@Service
public class ArchivoServiceImpl implements ArchivoService{

    @Autowired
    private ArchivoRepository archivoRepository;

    @Override
    public List<Archivo> listaArchivos() {
        return archivoRepository.findAll();
    }

    @Override
    public void guardarArchivo(Archivo archivo) {
        archivoRepository.save(archivo);
    }

}
