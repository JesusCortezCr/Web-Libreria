package com.example.demo.controllers;

public class Libro{
     private String nombre;
    private String autor;
    private int fechaPublicacion;
    private String paginas;
    private String tipo;

    public Libro(String nombre, String autor, int fechaPublicacion, String paginas, String tipo) {
        this.nombre = nombre;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.paginas = paginas;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(int fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
