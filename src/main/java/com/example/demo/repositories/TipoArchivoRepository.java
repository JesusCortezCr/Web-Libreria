package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.TipoArchivo;

public interface TipoArchivoRepository extends JpaRepository <TipoArchivo, Integer> {

}
