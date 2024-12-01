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
    private String usuarioId;
    private String recursoId;
    private String ubicacion;
    private String sede;
    private String estado;
    private LocalDateTime fechaPrestamo;
    private LocalDateTime fechaDevolucion;
    // private Recurso recurso;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaEliminacion;
    private String modificadoPor;
    private String observaciones;

    public Prestamo(String id, String usuarioId, String recursoId, String ubicacion, String sede, String estado,
                    LocalDateTime fechaDevolucion, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion,
                    LocalDateTime fechaEliminacion, String modificadoPor, String observaciones) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.recursoId = recursoId;
        this.ubicacion = ubicacion;
        this.sede = sede;
        this.estado = estado;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
        this.modificadoPor = modificadoPor;
        this.observaciones = observaciones;
    }

    public void setRecursoId(String recursoId) {
        this.recursoId = recursoId;
    }
    
    public String getModificadoPor() {
        return modificadoPor;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
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
