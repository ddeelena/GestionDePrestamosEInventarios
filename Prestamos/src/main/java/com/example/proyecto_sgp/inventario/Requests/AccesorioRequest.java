package com.example.proyecto_sgp.inventario.Requests;

import com.example.proyecto_sgp.inventario.Enums.EstadosElementos;
import com.example.proyecto_sgp.inventario.Enums.TipoDeElementos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AccesorioRequest {

    @NotNull(message = "El elemento debe contener un nombre")
    private String nombre;

    private String descripcion;

    private TipoDeElementos tipo;

    @NotNull(message = "El estado es obligatorio")
    private EstadosElementos estado;

    @NotNull(message = "La fecha de creación es obligatoria")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoDeElementos getTipo() {return tipo;}

    public void setTipo(TipoDeElementos tipo) {this.tipo = tipo;}

    public EstadosElementos getEstado() {
        return estado;
    }

    public void setEstado(EstadosElementos estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
