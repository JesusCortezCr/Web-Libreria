package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contactanos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Complete el campo porfavor")
    @Size(max = 20 , message = "Maximo 20 caracteres")
    private String nombre;

    @NotBlank(message = "Complete su Correo")
    @Size(max = 50 , message = "Maximo 50 caracteres")
    @Email(message = "Escriba Correctamente")
    private String correo;

    @NotBlank(message = "Complete el campo porfavor")
    @Size(max = 255 , message = "Maximo 255 caracteres")
    private String mensaje;

    @NotBlank(message = "Complete su numero telefonico")
    @Size(max = 11 , message = "Maximo 11 caracteres")
    private String numero;

}
