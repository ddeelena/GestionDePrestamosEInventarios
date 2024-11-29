package com.example.proyecto_sgp.Prestamos_service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {
    private String id;
    private String nombre;
    private String email;

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }
}