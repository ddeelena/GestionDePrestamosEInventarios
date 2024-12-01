package co.edu.cue.validacionUsuarios.model;

import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ListaNegraUsuarios")
@ToString
public class Usuario {

    private String nombre;
    private String id;
    private boolean condicion ;
    private String descripcion;

    public Usuario(String nombre, String id, boolean condicion, String descripcion) {
        this.nombre = nombre;
        this.id = id;
        this.condicion = condicion;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
