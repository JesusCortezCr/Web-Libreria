package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Complete el campo porfavor")
    @Size(max = 20, message = "Maximo 20 caracteres")
    private String nombre;

    @NotBlank(message = "Complete su Correo")
    @Email(message = "Escriba Correctamente")
    private String correo;

    @NotBlank(message = "Complete su número")
    @Size(max = 15, message = "Máximo 15 caracteres")
    private String numero; 

    @NotBlank(message = "Complete el campo porfavor")
    @Size(max = 255, message = "Maximo 255 caracteres")
    private String mensaje;



}
