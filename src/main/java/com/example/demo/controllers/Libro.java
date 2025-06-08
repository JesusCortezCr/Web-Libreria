package com.example.demo.controllers;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class Libro {
    private String nombre;
    private String autor;
    private int fechaPublicacion;
    private String paginas;
    private String tipo;
}
