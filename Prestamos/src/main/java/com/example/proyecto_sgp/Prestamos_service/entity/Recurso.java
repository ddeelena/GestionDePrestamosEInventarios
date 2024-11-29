package com.example.proyecto_sgp.Prestamos_service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "recursos")
public class Recurso {

    @Id
    private String id;

    private String codigo;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String sede;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaEliminacion;

    public Recurso(String id, String codigo, String nombre, String descripcion, String tipo, String sede, 
                   String estado, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, 
                   LocalDateTime fechaEliminacion) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.sede = sede;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
    }
}

