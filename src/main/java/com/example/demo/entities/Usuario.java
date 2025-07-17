package com.example.demo.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Column(name = "id_usuario")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotBlank(message = "Campo obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "Campo obligatorio")
    private String apellido;
    
    @NotBlank(message = "Campo obligatorio")
    private String correo;

    @NotBlank(message = "Campo obligatorio")
    private String contrasenia;

    @NotBlank(message = "Campo obligatorio")
    private String telefono;
    
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol id_rol;

}
