package com.example.demo.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//Corregir si se borra afectaa la base de datos
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private String nombre;
    private String autor;
    private int fechaPublicacion;
    private String paginas;
    private String tipo;
}
