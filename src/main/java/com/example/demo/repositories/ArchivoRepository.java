package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Archivo;

public interface ArchivoRepository extends JpaRepository<Archivo,Long> {

}
