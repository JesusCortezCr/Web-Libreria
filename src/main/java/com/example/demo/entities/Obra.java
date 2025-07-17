package com.example.demo.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Obras")
@NoArgsConstructor
@AllArgsConstructor
public class Obra {
    
    @Id
    @Column(name = "id_obra")
    private Long id;

    @Column(length = 100, nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private String archivo;

    private String portada;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition =  "ENUM('pendiente','publicado','rechazado') DEFAULT 'pendiente'")
    private EstadoObra estado = EstadoObra.pendiente;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable =  false)
    private TipoObra tipo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario autor;

    public enum EstadoObra{
        pendiente, publicado, rechazado
    }
}
