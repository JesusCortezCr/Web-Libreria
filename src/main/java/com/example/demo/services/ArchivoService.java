package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Archivo;

public interface ArchivoService {

    List<Archivo> listaArchivos();
    void guardarArchivo(Archivo archivo);

}
