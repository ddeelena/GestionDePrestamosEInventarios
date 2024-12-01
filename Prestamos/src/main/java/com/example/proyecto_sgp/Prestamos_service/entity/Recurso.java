package com.example.proyecto_sgp.Prestamos_service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "recursos")
public class Recurso {

    @Id
    private String id;

    @Field("codigo")
    private String codigo;

    @Field("nombre")
    private String nombre;

    @Field("descripcion")
    private String descripcion;

    @Field("tipo_recurso")
    private String tipo; // Ajustado para coincidir con el campo de la base de datos

    @Field("sede")
    private String sede;

    @Field("estado")
    private String estado;

    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Field("fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Field("fecha_eliminacion")
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