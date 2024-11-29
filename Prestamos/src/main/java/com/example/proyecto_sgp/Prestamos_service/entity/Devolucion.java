package com.example.proyecto_sgp.Prestamos_service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "devoluciones")
public class Devolucion {

    @Id
    private String id;

    private Recurso recurso;
    private Usuario usuario;
    private String ubicacion;
    private String observaciones;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaEliminacion;

    public Devolucion(String id, Recurso recurso, Usuario usuario, String ubicacion, String observaciones, 
                      String estado, LocalDateTime fechaCreacion, 
                      LocalDateTime fechaModificacion, LocalDateTime fechaEliminacion) {
        this.id = id;
        this.recurso = recurso;
        this.usuario = usuario;
        this.ubicacion = ubicacion;
        this.observaciones = observaciones;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
    }
}
