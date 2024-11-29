package com.example.proyecto_sgp.Prestamos_service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "prestamos")
public class Prestamo {

    @Id
    private String id;
    private Usuario usuario;
    private String recursoId;
    private String ubicacion;
    private String sede;
    private String estado;
    private LocalDateTime fechaDevolucion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaEliminacion;
    private String modificadoPor;
    private String observaciones;

    public Prestamo(String id, Usuario usuario, String recursoId, String ubicacion, String sede, String estado,
                    LocalDateTime fechaDevolucion, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion,
                    LocalDateTime fechaEliminacion) {
        this.id = id;
        this.usuario = usuario;
        this.recursoId = recursoId;
        this.ubicacion = ubicacion;
        this.sede = sede;
        this.estado = estado;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
    }

    public void setRecursoId(String recursoId) {
        this.recursoId = recursoId;
    }
    
    public String getModificadoPor() {
        return modificadoPor;
    }
    
    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
