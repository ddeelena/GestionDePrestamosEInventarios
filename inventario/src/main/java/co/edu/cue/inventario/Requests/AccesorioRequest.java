package co.edu.cue.inventario.Requests;

import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mongodb.lang.NonNull;
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
