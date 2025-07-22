package com.example.demo.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDate fechaSubida;
    private String descripcion;
    private String portada;
    private String archivo_pdf;
    
private String nombre; 
    private String rutaArchivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_archivo_id")
    private TipoArchivo tipoArchivo;

    @OneToMany(mappedBy="archivo")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy="archivo")
    private List<Favorito> favoritos;
}
