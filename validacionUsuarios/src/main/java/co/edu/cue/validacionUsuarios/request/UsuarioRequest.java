package co.edu.cue.validacionUsuarios.request;

import jakarta.validation.constraints.NotNull;


public class UsuarioRequest {

    @NotNull(message = "El usuario debe tener una identifiaacion")
    private String id;
    @NotNull(message = "El usuario debe tener un nombre")
    private String nombre;
    @NotNull(message = "El usuario debe tener una condicion")
    private boolean condicion;
    @NotNull(message = "El usuario debe tener una descripcion")
    private String descripcion;


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

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
